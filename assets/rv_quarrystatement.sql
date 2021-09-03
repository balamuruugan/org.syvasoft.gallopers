/***/DROP VIEW  RV_QuarryStatement;
/***/CREATE VIEW RV_QuarryStatement AS 
/***/SELECT 
	mm.AD_Org_ID,
	0 Idx,
	 mm.QuarryProductionType,
	 CASE mm.QuarryProductionType WHEN 'J' THEN 'Jack Hammer' ELSE 'GRAWELLER' END QuarryProductionTypeName,
	 m.PM_Machinery_ID, m.dateacct,
	 COALESCE(TF.DocumentNo, cb.DocumentNo, t.DocumentNo) DocumentNo,
	 mm.MachineryNo || ' | ' || COALESCE(p.Name, c.name) || COALESCE(', '|| m.description,'') description, 
	 p.Name as Product,m.Qty Qty,u.Name as Uom,m.rate,
	 m.expense,
	 null::numeric outtonne,
	 m.M_Product_ID,
	 c.c_elementvalue_id,
	 c.name accounthead
FROM 
	PM_MachineStmt m  INNER JOIN PM_Machinery mm ON mm.PM_Machinery_ID = m.PM_Machinery_ID	 	
  	INNER JOIN c_elementvalue c ON c.c_elementvalue_id = m.c_elementvalue_id
  	LEFT OUTER JOIN M_Product p ON m.M_Product_ID=p.M_Product_ID
  	LEFT OUTER JOIN C_Uom u ON u.C_Uom_ID=m.C_Uom_ID
  	LEFT OUTER JOIN TF_Fuel_Issue tf ON m.TF_Fuel_Issue_ID=tf.TF_Fuel_Issue_ID
  	LEFT OUTER JOIN C_Payment cb ON m.C_Payment_ID = cb.C_Payment_ID
	LEFT OUTER JOIN TF_TripSheet T ON t.TF_TripSheet_ID = m.TF_TripSheet_ID
	
	
	
WHERE 
	mm.QuarryProductionType  IS NOT NULL AND COALESCE(m.expense,0) > 0 AND (tf.IssueType='E' OR tf.IssueType IS NULL)

UNION

/***/SELECT 
	mm.AD_Org_ID,
	0 Idx,
	 mm.QuarryProductionType,
	 CASE mm.QuarryProductionType WHEN 'J' THEN 'Jack Hammer' ELSE 'GRAWELLER' END QuarryProductionTypeName,
	 m.PM_Machinery_ID, m.dateacct,
	 COALESCE(TF.DocumentNo, cb.DocumentNo, t.DocumentNo) DocumentNo,
	 mm.MachineryNo || ' | ' || COALESCE(p.Name, c.name) || COALESCE(', '|| m.description,'') description, 
	 p.Name as Product,m.Qty Qty,u.Name as Uom,m.rate,
	 m.Income,
	 null::numeric outtonne,
	 m.M_Product_ID,
	 c.c_elementvalue_id,
	 c.name accounthead
FROM 
	PM_MachineStmt m  INNER JOIN PM_Machinery mm ON mm.PM_Machinery_ID = m.PM_Machinery_ID	 	
  	INNER JOIN c_elementvalue c ON c.c_elementvalue_id = m.c_elementvalue_id
  	LEFT OUTER JOIN M_Product p ON m.M_Product_ID=p.M_Product_ID
  	LEFT OUTER JOIN C_Uom u ON u.C_Uom_ID=m.C_Uom_ID
  	LEFT OUTER JOIN TF_Fuel_Issue tf ON m.TF_Fuel_Issue_ID=tf.TF_Fuel_Issue_ID
  	LEFT OUTER JOIN C_Payment cb ON m.C_Payment_ID = cb.C_Payment_ID
	LEFT OUTER JOIN TF_TripSheet T ON t.TF_TripSheet_ID = m.TF_TripSheet_ID
	
	
	
WHERE 
	
	mm.QuarryProductionType  = 'J' AND COALESCE(m.Income,0) > 0 


UNION

/***/SELECT
	t.AD_Org_ID,
	0 idx,
	t.QuarryProductionType,
	CASE t.QuarryProductionType WHEN 'J' THEN 'Jack Hammer' ELSE 'GRAWELLER' END QuarryProductionTypeName,
	t.PM_Machinery_ID,
	t.DateAcct,
	t.DocumentNo,
	p.Name description,
	p.Name product,
	t.qty qty,
	u.Name UOM,
	t.Rate rate,
	t.Amt expense,
	NULL TotalMT,
	p.M_Product_ID,
	 c.c_elementvalue_id,
	 c.name accounthead
	 
FROM
	TF_Fuel_Issue t INNER JOIN M_Product p 
	 ON t.M_Product_ID = p.M_Product_ID				
	INNER JOIN C_UOM u ON t.C_UOM_ID = u.C_UOM_ID
	LEFT OUTER JOIN c_elementvalue c ON c.c_elementvalue_id = t.c_elementvalue_id
	
WHERE 
	t.QuarryProductionType  IS NOT NULL  AND t.DocStatus = 'CO' 
	AND t.IssueType='E'


UNION


/***/SELECT
	m.AD_Org_ID,
	1 idx,
	tp.QuarryProductionType,
	CASE tp.QuarryProductionType WHEN 'J' THEN 'Jack Hammer' ELSE 'GRAWELLER' END QuarryProductionTypeName,
	t.PM_Machinery_ID,
	t.DateReport,
	t.DocumentNo,
	m.MachineryNo || ' | ' || p.Name description,
	p.Name product,
	null qty,
	'MT' UOM,
	null rate,
	null expense,
	tp.TotalMT,
	p.M_Product_ID,
	null,null
	 
FROM
	TF_TripSheet t INNER JOIN TF_TripSheetProduct tp 
	 ON t.TF_TripSheet_ID = tp.TF_TripSheet_ID
	INNER JOIN M_Product p 
	 ON tp.M_Product_ID = p.M_Product_ID
	INNER JOIN PM_Machinery m 
	 ON m.PM_Machinery_ID = t.PM_Machinery_ID
	
WHERE 
	t.DocStatus='CO' AND  tp.QuarryProductionType IS NOT NULL

ORDER BY	
	QuarryProductionType,DateAcct,documentno,1;