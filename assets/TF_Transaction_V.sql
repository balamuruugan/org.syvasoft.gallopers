DROP VIEW TF_Transaction_V;

CREATE OR REPLACE VIEW TF_Transaction_V AS

SELECT
	t.M_Transaction_ID,
	t.AD_Client_ID,
	t.AD_Org_ID,
	t.IsActive,
	t.Created,
	t.Createdby,
	t.Updated,
	t.Updatedby,
	t.MovementType,
	t.M_Locator_ID,
	t.M_Product_ID,
	t.MovementDate,
	t.MovementQty,
	t.M_InventoryLine_ID,
	t.M_MovementLine_ID,
	t.M_InOutLine_ID,
	t.M_ProductionLine_ID,
	t.C_ProjectIssue_ID,
	t.M_AttributeSetInstance_ID,
	t.PP_Cost_Collector_ID,
	t.M_Transaction_UU,
	t.TF_Boulder_Receipt_ID,	
	l.value locator_name,
	w.M_Warehouse_ID,
	w.Name warehouse_name,
	mt.Name movementTypeName,
	f.TF_Fuel_Issue_ID,
	f.PrevIssueMeter,
	F.IssueMeter,
	F.IssueMeter - f.PrevIssueMeter as RunningMeter,
	CASE 
	  WHEN f.Rate IS NOT NULL THEN f.Rate
	  WHEN io.DocumentNo IS NOT NULL THEN 
		(SELECT MAX(invL.PriceEntered) FROM M_MatchInv mInv INNER JOIN C_InvoiceLine invL 
		 ON mInv.C_InvoiceLine_ID = invL.C_InvoiceLine_ID WHERE il.M_InoutLine_ID = mInv.M_InoutLine_ID 
		 AND t.M_Product_ID = mInv.M_Product_ID)
	  WHEN io.DocumentNo IS NOT NULL THEN 
	    (SELECT MAX(CASE WHEN cm.Qty = 0 THEN cm.NewCostPrice ELSE ABS(cm.Amt / cm.Qty) END)
		FROM M_CostMovement_v cm WHERE il.M_InOutLine_ID = cm.M_InOutLine_ID)	  
	  WHEN mov.DocumentNo IS NOT NULL THEN 
	    (SELECT MAX(CASE WHEN cm.Qty = 0 THEN cm.NewCostPrice ELSE ABS(cm.Amt / cm.Qty) END)
		FROM M_CostMovement_v cm WHERE movl.M_MovementLine_ID = cm.M_MovementLine_ID)
	  WHEN inv.DocumentNo IS NOT NULL THEN
	    (SELECT MAX(CASE WHEN cm.Qty = 0 THEN cm.NewCostPrice ELSE ABS(cm.Amt / cm.Qty) END)
		FROM M_CostMovement_v cm WHERE invl.M_InventoryLine_ID = cm.M_InventoryLine_ID)
	  --WHEN p.DocumentNo IS NOT NULL THEN p.DocumentNo
	END Rate,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN io.DocumentNo
	  WHEN inv.DocumentNo IS NOT NULL THEN inv.DocumentNo
	  WHEN mov.DocumentNo IS NOT NULL THEN mov.DocumentNo
	  WHEN p.DocumentNo IS NOT NULL THEN p.DocumentNo
	END DocumentNo,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN io.DocStatus
	  WHEN inv.DocumentNo IS NOT NULL THEN inv.DocStatus
	  WHEN mov.DocumentNo IS NOT NULL THEN mov.DocStatus
	  WHEN p.DocumentNo IS NOT NULL THEN p.DocStatus
	  ELSE 'CO'
	END DocStatus,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN COALESCE(io.Description,'') || ', ' || COALESCE(il.Description, ' ')
	  WHEN inv.DocumentNo IS NOT NULL THEN COALESCE(inv.Description,'') || ', ' || COALESCE(invl.Description, ' ')
	  WHEN mov.DocumentNo IS NOT NULL THEN COALESCE(mov.Description,'') || ', ' || COALESCE(movl.Description, ' ')
	  WHEN p.DocumentNo IS NOT NULL THEN COALESCE(p.Description,'') || ', ' || COALESCE(pl.Description, ' ')
	END Description,
	CASE
	  WHEN io.DocumentNo IS NOT NULL THEN (SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID = io.C_BPartner_ID)
	  WHEN f.IssueType = 'P' AND f.Vehicle_ID IS NOT NULL THEN
		(SELECT bp.Name  FROM TF_RentedVehicle r INNER JOIN C_BPartner bp ON r.C_BPartner_ID = bp.C_BPartner_ID 
		 WHERE f.Vehicle_ID = r.M_Product_ID)
	  WHEN f.IssueType = 'P' AND f.C_Project_ID IS NOT NULL THEN
		(SELECT bp.Name FROM C_Project p INNER JOIN C_BPartner bp ON bp.C_BPartner_ID = p.C_BPartner_ID 
			WHERE p.C_Project_ID = f.C_Project_ID)	  
	END bpName,
	CASE
	  WHEN io.DocumentNo IS NOT NULL THEN io.C_BPartner_ID
	  WHEN f.IssueType = 'P' AND f.Vehicle_ID IS NOT NULL THEN
		(SELECT bp.C_BPartner_ID  FROM TF_RentedVehicle r INNER JOIN C_BPartner bp ON r.C_BPartner_ID = bp.C_BPartner_ID 
		 WHERE f.Vehicle_ID = r.M_Product_ID)
	  WHEN f.IssueType = 'P' AND f.C_Project_ID IS NOT NULL THEN
		(SELECT bp.C_BPartner_ID FROM C_Project p INNER JOIN C_BPartner bp ON bp.C_BPartner_ID = p.C_BPartner_ID 
			WHERE p.C_Project_ID = f.C_Project_ID)	  
	END C_BPartner_ID
	
FROM 
   M_Transaction t INNER JOIN M_Locator l
    ON l.M_Locator_ID = t.M_Locator_ID
   INNER JOIN M_Warehouse w 
    ON w.M_Warehouse_ID = l.M_Warehouse_ID
   INNER JOIN AD_Ref_LIST mt
    ON AD_Reference_ID = 189 AND mt.value = t.movementType
   LEFT OUTER JOIN M_InoutLine il
    ON t.M_InoutLine_ID = il.M_InoutLine_ID
   LEFT OUTER JOIN M_InOut io
    ON io.M_InOut_ID = il.M_InOut_ID
   LEFT OUTER JOIN M_InventoryLine invl
    ON invl.M_InventoryLine_ID = t.M_InventoryLine_ID
   LEFT OUTER JOIN M_Inventory inv
    ON inv.M_Inventory_ID = invl.M_Inventory_ID
   LEFT OUTER JOIN M_MovementLine movl
    ON movl.M_MovementLine_ID = t.M_MovementLine_ID
   LEFT OUTER JOIN M_Movement mov
    ON mov.M_Movement_ID = movl.M_Movement_ID
   LEFT OUTER JOIN M_ProductionLine pl
    ON pl.M_ProductionLine_ID = t.M_ProductionLine_ID
   LEFT OUTER JOIN M_Production p
    ON p.M_Production_ID = pl.M_Production_ID
   LEFT OUTER JOIN TF_Fuel_Issue f 
    ON (f.M_Inventory_ID = inv.M_Inventory_ID OR f.M_InOut_ID = io.M_InOut_ID)

;
