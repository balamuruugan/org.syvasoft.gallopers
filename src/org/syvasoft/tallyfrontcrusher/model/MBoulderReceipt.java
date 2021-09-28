package org.syvasoft.tallyfrontcrusher.model;



import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPeriod;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPricing;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTransaction;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.model.MCost.QtyCost;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBoulderReceipt extends X_TF_Boulder_Receipt {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6229740359935434019L;

	public MBoulderReceipt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBoulderReceipt(Properties ctx, int TF_Boulder_Receipt_ID,
			String trxName) {
		super(ctx, TF_Boulder_Receipt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
				
		if(newRecord || is_ValueChanged(COLUMNNAME_C_Project_ID)) {
			if(getC_Project_ID() > 0) {
				TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
				//setJobWork_Product_ID(proj.getJobWork_Product_ID());				
				MWeighmentEntry weightmentEntry = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());				
				MRentedVehicle rentedVehichle = new MRentedVehicle(getCtx(), weightmentEntry.getTF_RentedVehicle_ID(), get_TrxName());				
				if(rentedVehichle.getC_BPartner_ID() == weightmentEntry.getC_BPartner_ID())
				{
					//with transportation
					setJobWork_Product_ID(proj.getJobWork_Product_ID());								
				}
				else
				{
					//without with transportation					
					setJobWork_Product_ID(proj.getJobWorkWOTrans_Product_ID());
					//set own vehicle
				}							
				BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), proj.getJobWork_Product_ID(), getDateAcct()) ;
				setJobwork_StdPrice(purchasePrice);
			}
		}
		
				
		return super.beforeSave(newRecord);
	}
	
	public void createSubcontractMovement() {
		if(getTF_RMSubcon_Movement_ID() > 0)
			return;
		
		if(getC_Project_ID() > 0 && TF_SEND_TO_Production.equals(getTF_Send_To())) {
			TF_MProject proj = TF_MProject.getCrusherProductionSubcontractByWarehouse(getM_Warehouse_ID());
			if(proj == null) {				
				proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
			}
			MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
			if(st.isTrackMaterialMovement() ) {
				int matMov_ID = MSubcontractMaterialMovement.createRawmaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(),
						proj.getC_Project_ID(), proj.getC_BPartner_ID(), getM_Product_ID(), getTF_WeighmentEntry_ID(), getQtyReceived());
				setTF_RMSubcon_Movement_ID(matMov_ID);
			}
			
		}
		else if(TF_SEND_TO_Production.equals(getTF_Send_To())) {
			
			int matMov_ID = MSubcontractMaterialMovement.createRawmaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(),
					0, 0, getM_Product_ID(), getTF_WeighmentEntry_ID(), getQtyReceived());			
			setTF_RMSubcon_Movement_ID(matMov_ID);
		}
		else if(getTF_WeighmentEntry_ID() > 0){
			MBoulderMovement.createBoulderReceipt(get_TrxName(), getDateReceipt(), getAD_Org_ID(), getM_Product_ID(), getQtyReceived(),
					getTF_WeighmentEntry_ID(), getM_Warehouse_ID());
		}
			
	}
	public void createFromWeighmentEntry(MWeighmentEntry entry) {
		setAD_Org_ID(entry.getAD_Org_ID());
		setDateReceipt(entry.getGrossWeightTime());
		setDateAcct(entry.getGrossWeightTime());
		TF_MProject proj = new TF_MProject(getCtx(), entry.getC_Project_ID(), get_TrxName());	
		
		if(proj != null && proj.getC_Project_ID() > 0) {
			setC_Project_ID(entry.getC_Project_ID());
			setSubcontractor_ID(entry.getC_BPartner_ID());
			setTF_Quarry_ID(proj.getTF_Quarry_ID());									
			setC_UOM_ID(proj.getC_UOM_ID());
		}
		if(entry.getTF_Quarry_ID() > 0) {
			setTF_Quarry_ID(entry.getTF_Quarry_ID());
			TF_MProduct prod = (TF_MProduct) entry.getM_Product();
			setC_UOM_ID(prod.getC_UOM_ID());
		}
		setM_Product_ID(entry.getM_Product_ID());		
		setQtyReceived( new BigDecimal(entry.getNetWeight().doubleValue()/1000));
		
		setM_Warehouse_ID(entry.getM_Warehouse_ID());
		
		//set destination warehouse where the boulder is received
		if(entry.getTF_Send_To().equals(TF_SEND_TO_Production)) {
			MProductionPlant pp = new MProductionPlant(getCtx(), entry.getTF_ProductionPlant_ID(), get_TrxName());
			setM_Warehouse_ID(pp.getM_Warehouse_ID());
		}
		else {
			MOrgInfo orgIn = MOrgInfo.get(getCtx(), getAD_Org_ID(), get_TrxName()); 
			setM_Warehouse_ID(orgIn.getM_Warehouse_ID());
		}
		setDescription(entry.getDescription());
		setVehicleNo(entry.getVehicleNo());		
		setTF_Send_To(entry.getTF_Send_To());
		setTF_ProductionPlant_ID(entry.getTF_ProductionPlant_ID());
		setTF_BlueMetal_Type(entry.getTF_BlueMetal_Type());
		setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
		setTF_WeighmentEntry_ID(entry.getTF_WeighmentEntry_ID());		
	}
	
	public void createSubcontractInvoice() {
		TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
		MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
		if(!st.isCreateBoulderReceipt())
			return;
		int invoiceItem_id = 0;
		int priceItem_id = 0;
		String priceItemName = null;
		
		
		if(st.getInvoiceFor().equals(MSubcontractType.INVOICEFOR_Jobwork))
			invoiceItem_id = getJobWork_Product_ID();
		else
			invoiceItem_id = getM_Product_ID();
		
		if(st.getInvoicePriceFrom().equals(MSubcontractType.INVOICEPRICEFROM_Jobwork)) {
			priceItem_id = getJobWork_Product_ID();
			priceItemName = getJobWork_Product().getName();
		}
		else {
			priceItem_id = getM_Product_ID();
			priceItemName = getM_Product().getName();
		}
		
		//Crusher Production Subcontract Purchase		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem_id, getDateAcct()) ;
		if(purchasePrice == null) 
			throw new AdempiereException("Please setup Contract Price for " + priceItemName + "!");
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), proj.getC_BPartner_ID(), get_TrxName());
		
		//Purchase Invoice Header
		MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(config.getTransporterInvoiceDocType_ID());	// AP Invoice		
		invoice.setDateInvoiced(getDateReceipt());
		invoice.setDateAcct(getDateAcct());
		invoice.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		//
		
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		invoice.setVehicleNo(getVehicleNo());
		
		String desc = "Boulder Receipt: " + getDocumentNo();
		if(getTF_WeighmentEntry_ID() > 0 )
			desc = desc + " | Ticket No: " + getTF_WeighmentEntry().getDocumentNo();
		invoice.setDescription(desc);
		
		//Price List
		int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();		
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		if(invoice.getC_Currency_ID() == 0)
			invoice.setC_Currency_ID(MClient.get(Env.getCtx()).getC_Currency_ID());
		
				
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Vehicle Rental Charge
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		invLine.setM_Product_ID(invoiceItem_id , true);				
		
		invLine.setQty(getQtyReceived());
		invLine.setDescription(getDescription());
		
		invLine.setPriceActual(purchasePrice);
		invLine.setPriceList(purchasePrice);
		invLine.setPriceLimit(purchasePrice);
		invLine.setPriceEntered(purchasePrice);
		
		invLine.setC_Tax_ID(1000000);
		invLine.saveEx();
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		setSubcon_Invoice_ID(invoice.getC_Invoice_ID());
		
		if(proj.getC_BPartnerSubcon2_ID() > 0) {
			CreateSubcontract2Invoice(proj.getC_BPartnerSubcon2_ID(), proj.getM_ProductSubcon2_ID(), proj.getPriceSubcon2());
		}
	}
	
	public void CreateSubcontract2Invoice(int C_BPartnerSubcon2_ID, int M_ProductSubcon2_ID, BigDecimal priceSubcon2) {
		if(M_ProductSubcon2_ID == 0)
			throw new AdempiereException("Please specify Product (Subcontract 2) in " + getC_Project().getName() +
					" Subcontract to Create Subcontract 2 Invoice!");
		
		if(priceSubcon2 == null || priceSubcon2.doubleValue() == 0)
			throw new AdempiereException("Please specify Contract Price (Subcontract 2) in " + getC_Project().getName() +
					" Subcontract to Create Subcontract 2 Invoice!");
		
		int invoiceItem_id = M_ProductSubcon2_ID;
		
		//Crusher Production Subcontract Purchase		
		BigDecimal purchasePrice = priceSubcon2;		
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BPartnerSubcon2_ID, get_TrxName());
		
		//Purchase Invoice Header
		MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(config.getTransporterInvoiceDocType_ID());	// AP Invoice		
		invoice.setDateInvoiced(getDateReceipt());
		invoice.setDateAcct(getDateAcct());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		invoice.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
		//
		
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		invoice.setVehicleNo(getVehicleNo());
		
		String desc = "Boulder Receipt: " + getDocumentNo();
		if(getTF_WeighmentEntry_ID() > 0 )
			desc = desc + " | Ticket No: " + getTF_WeighmentEntry().getDocumentNo();
		invoice.setDescription(desc);
		
		//Price List
		int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();		
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		if(invoice.getC_Currency_ID() == 0)
			invoice.setC_Currency_ID(MClient.get(Env.getCtx()).getC_Currency_ID());
		
				
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Vehicle Rental Charge
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		invLine.setM_Product_ID(invoiceItem_id , true);				
		
		invLine.setQty(getQtyReceived());
		invLine.setDescription(getDescription());
		
		invLine.setPriceActual(purchasePrice);
		invLine.setPriceList(purchasePrice);
		invLine.setPriceLimit(purchasePrice);
		invLine.setPriceEntered(purchasePrice);
		
		invLine.setC_Tax_ID(1000000);
		invLine.saveEx();
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		setSubcon2_Invoice_ID(invoice.getC_Invoice_ID());
	}
	
		
	public String processIt(String DocAction) {
		MWeighmentEntry entry = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		if(entry.isProcessed())
			throw new AdempiereException("Ticket No: " + getDocumentNo() + " is already processed!");
		
		if(entry.getWeighmentEntryType().equals(MWeighmentEntry.WEIGHMENTENTRYTYPE_StockToCrusher))
				return null;
		
		String m_processMsg = null;
		TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());		
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			
			MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			int defaultLocatorID = warehouse.getDefaultLocator().getM_Locator_ID();
			createSubcontractMovement();
			if(getTF_Send_To().equals(TF_SEND_TO_SubcontractProduction)) {
										
				// to check Requires Consolidate Invoice				
				if(!proj.isRequiredConsolidateInv())							
					createSubcontractInvoice();
				else
					createSubcontractServiceReceipt();
			
				setDocStatus(DOCSTATUS_Completed);
				setProcessed(true);
				return null;
			}
			else if(getC_Project_ID() == 0) {
				
				createInternalUseInventory();
			}
			else {
				if(getM_Product().isStocked()) {
					//Update Storage for the received Product from the Joborder.
					if (!MStorageOnHand.add(getCtx(), getM_Warehouse_ID(),
							defaultLocatorID,
							getM_Product_ID(),
							0,
							getQtyReceived(),getDateAcct(),
							get_TrxName()))
						{
							String lastError = CLogger.retrieveErrorString("");
							m_processMsg = "Cannot correct Inventory OnHand (MA) [" + getM_Product().getValue() + "] - " + lastError;				
							return m_processMsg;
						}
					//Update Transaction History
					MTransaction mtrx = new MTransaction (getCtx(), getAD_Org_ID(),
						"J+", defaultLocatorID,
						getM_Product_ID(), 0,
						getQtyReceived(), getDateAcct(), get_TrxName());
					mtrx.set_ValueOfColumn(MBoulderReceipt.COLUMNNAME_TF_Boulder_Receipt_ID, getTF_Boulder_Receipt_ID());
					if (!mtrx.save())
					{
						m_processMsg = "Could not create Material Transaction (MA) [" + getM_Product().getValue() + "]";			
						return m_processMsg;
					}
					
					//Update Costing Record...
					MAcctSchema as = (MAcctSchema) MGLPostingConfig.getMGLPostingConfig(getCtx()).getC_AcctSchema();			
					MCostDetail.createBoulderReceipt(as, getAD_Org_ID(), getM_Product_ID(), 0, getTF_Boulder_Receipt_ID()
							, 0, getJobwork_StdPrice().multiply(getQtyReceived()), getQtyReceived(), getDescription(), get_TrxName());
				
					setM_Transaction_ID(mtrx.getM_Transaction_ID());
				}
			
				// to check Requires Consolidate Invoice	
				if(!proj.isRequiredConsolidateInv())				
					createSubcontractInvoice();
				else
					createSubcontractServiceReceipt();
			}
			
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			entry.setProcessed(true);
			entry.saveEx();
			
			if(TF_SEND_TO_Production.equals(getTF_Send_To())) {
				m_processMsg = postCrusherProduction();
			}			
			
			createTransportMaterialReceipt();
			return m_processMsg;
				
		}	
			return null;
			
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		if(getSubcon2_Invoice_ID()>0) {			
			//throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getSubcon2_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			inv.saveEx();
			setSubcon2_Invoice_ID(0);
		}		
		if(getSubcon_Invoice_ID()>0) {			
			//throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getSubcon_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
				inv.reverseCorrectIt();
			inv.saveEx();			
			setSubcon_Invoice_ID(0);						
		}
		
		if(getTF_RMSubcon_Movement_ID()>0) {
			MSubcontractMaterialMovement mov = new MSubcontractMaterialMovement(getCtx(), getTF_RMSubcon_Movement_ID(), get_TrxName());
			mov.deleteEx(true);
			setTF_RMSubcon_Movement_ID(0);
		}		
		
		if(getC_Project_ID() == 0) { //Own Product Reverse
			if(getM_Inventory_ID() > 0) {
				MInventory inv = new MInventory(getCtx(), getM_Inventory_ID(), get_TrxName());
				if(!inv.getDocStatus().equals(DOCSTATUS_Reversed)) {
					inv.reverseCorrectIt();
					inv.saveEx();
				}
				setM_Inventory_ID(0);			
			}
		}
		else  {
			MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			int defaultLocatorID = warehouse.getDefaultLocator().getM_Locator_ID();
			String m_processMsg; 
			//Update Storage for the received Product from the Joborder.
			if (!MStorageOnHand.add(getCtx(), getM_Warehouse_ID(),
					defaultLocatorID,
					getM_Product_ID(),
					0,
					getQtyReceived().negate(),getDateAcct(),
					get_TrxName()))
				{
					String lastError = CLogger.retrieveErrorString("");
					m_processMsg = "Cannot correct Inventory OnHand (MA) [" + getM_Product().getValue() + "] - " + lastError;				
					throw new AdempiereException(m_processMsg);
				}
			//Update Transaction History
			MTransaction mtrx = new MTransaction (getCtx(), getAD_Org_ID(),
				"J+", defaultLocatorID,
				getM_Product_ID(), 0,
				getQtyReceived().negate(), getDateAcct(), get_TrxName());
			mtrx.set_ValueOfColumn(MBoulderReceipt.COLUMNNAME_TF_Boulder_Receipt_ID, getTF_Boulder_Receipt_ID());
			if (!mtrx.save())
			{
				m_processMsg = "Could not create Material Transaction (MA) [" + getM_Product().getValue() + "]";			
				throw new AdempiereException(m_processMsg);
			}
		}		
		if(getJobwork_Journal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getJobwork_Journal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			setJobwork_Journal_ID(0);
		}
		if(getJobwork_VarJournal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getJobwork_VarJournal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			setJobwork_VarJournal_ID(0);
		}
		if(getTF_Crusher_Production_ID() > 0) {
			MCrusherProduction crProd = new MCrusherProduction(getCtx(), getTF_Crusher_Production_ID(), get_TrxName());
			crProd.reverseIt();
			crProd.saveEx();
			setTF_Crusher_Production_ID(0);
		}		
		if(getTF_Quarry_Rent_ID()>0) {
			MQuarryRent rent = new MQuarryRent(getCtx(), getTF_Quarry_Rent_ID(), get_TrxName());
			rent.reverseIt();
			rent.saveEx();
			int rentID = rent.getTF_Quarry_Rent_ID();
			String sql = " UPDATE TF_Boulder_Receipt SET TF_Quarry_Rent_ID = NULL WHERE TF_Quarry_Rent_ID = " + rentID;
			DB.executeUpdate(sql, get_TrxName());
			rent.deleteEx(true);
		}
		MBoulderMovement.deleteBoulderMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		we.setProcessed(false);
		we.setStatus(MWeighmentEntry.STATUS_Unbilled);
		we.saveEx();
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		reverseServiceReceipts();
	}
		
	
	public String postCrusherProduction() {
		
		if(MSysConfig.getValue("AGGREGATE_STOCK_APPROACH","B", getAD_Client_ID(), getAD_Org_ID()).equals("B") )
			return null;
		
		String m_processMsg = null;
		//Create Crusher Production
		MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
		cProd.setAD_Org_ID(getAD_Org_ID());
		cProd.setTF_ProductionPlant_ID(getTF_ProductionPlant_ID());		
		cProd.setTF_BlueMetal_Type(getTF_BlueMetal_Type());
		cProd.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());
		cProd.setMovementDate(getDateReceipt());
		cProd.setC_UOM_ID(getC_UOM_ID());		
		cProd.setM_Warehouse_ID(getM_Warehouse_ID());
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		cProd.setRM_Product_ID(getM_Product_ID());
		cProd.setQtyUsed(getQtyReceived());
		cProd.setDescription("Created from Boulder Receipt : " + getDocumentNo());
		cProd.setDocStatus(DOCSTATUS_Drafted);
		cProd.setDocAction(DOCACTION_Prepare);
		cProd.saveEx();
		
		//Update Crusher Production Reference to Boulder Receipt
		setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
		
		cProd.createProduction(true);
		cProd.saveEx();		
		//End Create
		
		//Post Crusher Production
		m_processMsg = cProd.processIt(DOCACTION_Complete);
		if(m_processMsg == null)			
			cProd.saveEx();
		return m_processMsg;
	}
		
	private void createInternalUseInventory() {
		if(!getM_Product().isStocked())
			return;
		//Post Inventory Use Inventory to increase boulder stock with with its standard cost.
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		//Inventory Use Header
		MInventory inv = new MInventory(wh, get_TrxName());
		inv.setC_DocType_ID(1000026);
		String prdName = TF_MProduct.get(getCtx(), getM_Product_ID()).getName();
		String desc = "Issued " + prdName + " to " +  getVehicle().getName();
		if(getC_Project_ID() > 0) {
			desc = desc + " for " + getC_Project().getName();
		}
		inv.setDescription("Boulder Receipt: " + getDocumentNo());
		
		if(getTF_WeighmentEntry_ID() > 0)
			inv.addDescription(" Ticket No: " + getTF_WeighmentEntry().getDocumentNo());
		
		inv.addDescription(getDescription());
		inv.setMovementDate(getDateAcct());	
		
		inv.setDocStatus(DOCSTATUS_Drafted);
		inv.saveEx();
		
		//Inventory Use Line
		MInventoryLine line = new MInventoryLine(inv, wh.getDefaultLocator().get_ID(), getM_Product_ID(), 0, null, null, getQtyReceived().negate());
		MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
		line.setC_Charge_ID(config.getOwnMining_Charge_ID());
		line.setDescription(desc);
		//line.setCurrentCostPrice(getRate());
		line.saveEx();
		
		//Complete Inventory Use Document
		inv.processIt(MInventory.ACTION_Complete);
		inv.saveEx();
		
		//Update Inventory Use ID back to Fuel Issue Entry.
		setM_Inventory_ID(inv.getM_Inventory_ID());	
	}
	
	
	/***
	 * This method will be called after DOCACTION_Complete of Subcontractor Invoice 
	 * @param ctx
	 * @param SubconInvoice
	 * @param invoicedAmt
	 * @param trxName
	 */
	public static void postJobworkExpenseVarianceJournal(Properties ctx, MInvoice SubconInvoice, BigDecimal invoicedAmt, String trxName) {
		//Update back Invoice ID to Boulder Receipts.
		String sql = " Update TF_Boulder_Receipt SET Jobwork_PriceActual = ? WHERE Subcon_Invoice_ID = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(invoicedAmt);
		params.add(SubconInvoice.getC_Invoice_ID());		
		DB.executeUpdateEx(sql, params.toArray(), trxName);
		//End Update
		
		sql = " SELECT SUM(QtyReceived * (Jobwork_PriceActual - Jobwork_StdPrice)) AS expVar FROM TF_Boulder_Receipt WHERE Subcon_Invoice_ID = ?";
		BigDecimal expVar = DB.getSQLValueBD(trxName, sql, SubconInvoice.getC_Invoice_ID());
		
		//Do not create Expense Variance Journal.
		if(expVar == null || expVar.doubleValue() == 0)
			return;
		
		
		//Posting GL journal for Jobwork expense variances
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(ctx);
		int DrAcct = glConfig.getJobworkExpenseVarianceAcct_ID();
		int CrAcct = glConfig.getJobworkPayableClearingAcct_ID();
		
		//If the expense variance is negative,
		if(expVar.doubleValue() < 0) {			
			DrAcct = glConfig.getJobworkPayableClearingAcct_ID();
			CrAcct = glConfig.getJobworkExpenseVarianceAcct_ID();
		}
		
		//GL journal for Jobwork expense variances
		MJournal j = new MJournal(ctx, 0, trxName);
		j.setDescription("Generated Jobwork Expense Variances Journal Entry from Subcontract Invoice - " + SubconInvoice.getDocumentNo());
		j.setC_AcctSchema_ID(Env.getContextAsInt(ctx, "$C_AcctSchema_ID"));
		j.setC_Currency_ID(Env.getContextAsInt(ctx, "$C_Currency_ID"));
		j.setPostingType(MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(1000000);
		j.setDateDoc(SubconInvoice.getDateAcct());
		j.setDateAcct(SubconInvoice.getDateAcct());
		j.setDocStatus(DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(ctx, SubconInvoice.getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);
		j.saveEx();
		
		//Debtor Account
		MJournalLine jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(DrAcct);
		jl.setC_BPartner_ID(SubconInvoice.getC_BPartner_ID());
		jl.setM_Product_ID(glConfig.getJobWork_Product_ID());			
		jl.setUser1_ID(SubconInvoice.getUser1_ID()); // Quarry Profit Center
		jl.setAmtSourceDr(expVar.abs());
		jl.setAmtAcctDr(expVar.abs());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//Creditor Account
		jl = new MJournalLine(j);
		jl.setLine(20);			
		jl.setAccount_ID(CrAcct);
		jl.setC_BPartner_ID(SubconInvoice.getC_BPartner_ID());
		jl.setM_Product_ID(glConfig.getJobWork_Product_ID());			
		jl.setUser1_ID(SubconInvoice.getUser1_ID()); // Quarry Profit Center
		jl.setAmtSourceCr(expVar.abs());
		jl.setAmtAcctCr(expVar.abs());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		j.processIt(MJournal.ACTION_Complete);
		j.saveEx();
		
		//Update back Jobwork Expense Variance Journal ID to Boulder Receipts.
		sql = " Update TF_Boulder_Receipt SET Jobwork_VarJournal_ID = ? WHERE Subcon_Invoice_ID = ?  "  + 
				" AND Jobwork_PriceActual - Jobwork_StdPrice != 0 " ;
		params = new ArrayList<Object>();
		params.add(j.getGL_Journal_ID());
		params.add(SubconInvoice.getC_Invoice_ID());					
		DB.executeUpdateEx(sql, params.toArray(), trxName);
		//End Update
		
	}

	public void createSubcontractServiceReceipt() {
		TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
		MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
		if(!st.isCreateBoulderReceipt())
			return;
		int invoiceItem_id = 0;
		int priceItem_id = 0;
		String priceItemName = null;
		

		invoiceItem_id = getJobWork_Product_ID();
		
		priceItem_id = getJobWork_Product_ID();
		priceItemName = getJobWork_Product().getName();
		
		
		//Subcontract Purchase Price		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem_id, getDateAcct()) ;
		if(purchasePrice == null) 
			throw new AdempiereException("Please setup Contract Price for " + priceItemName + "!");
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), proj.getC_BPartner_ID(), get_TrxName());
		
		//Service Receipt Header		
		TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
		inout.materialReceipt = false;
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		inout.setIsSOTrx(false);
		inout.setC_DocType_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getMaterialReceipt_DocType_ID());
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);		
		inout.setDateAcct(getDateAcct());
		inout.setMovementDate(getDateAcct());		
		inout.setC_BPartner_ID(bp.getC_BPartner_ID());
		inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
		inout.setAD_User_ID(bp.getAD_User_ID());
		inout.setM_Warehouse_ID(getM_Warehouse_ID());
		inout.setPriorityRule(TF_MInOut.PRIORITYRULE_Medium);
		inout.setFreightCostRule(TF_MInOut.FREIGHTCOSTRULE_FreightIncluded);
		inout.saveEx(get_TrxName());
		
		//Material Receipt Line
		MInOutLine ioLine = new MInOutLine(inout);
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		
		ioLine.setLine(10);
		ioLine.setM_Product_ID(invoiceItem_id);
		ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		
		BigDecimal qty = getQtyReceived();		 
		
		ioLine.setQty(qty);
		ioLine.setC_UOM_ID(getC_UOM_ID());
		ioLine.set_ValueOfColumn("Price", purchasePrice);	
		ioLine.set_ValueOfColumn("DocStatus", "CO");				
		ioLine.saveEx(get_TrxName());
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
		
		inout.saveEx();
		
		
		if(proj.getC_BPartnerSubcon2_ID() > 0) {
			CreateSubcontract2ServiceReceipt(proj.getC_BPartnerSubcon2_ID(), proj.getM_ProductSubcon2_ID(), proj.getPriceSubcon2());
		}
		
		createTransportMaterialReceipt();
	}
	
	public void CreateSubcontract2ServiceReceipt(int C_BPartnerSubcon2_ID, int M_ProductSubcon2_ID, BigDecimal priceSubcon2) {
		if(M_ProductSubcon2_ID == 0)
			throw new AdempiereException("Please specify Product (Subcontract 2) in " + getC_Project().getName() +
					" Subcontract to Create Subcontract 2 Invoice!");
		
		if(priceSubcon2 == null || priceSubcon2.doubleValue() == 0)
			throw new AdempiereException("Please specify Contract Price (Subcontract 2) in " + getC_Project().getName() +
					" Subcontract to Create Subcontract 2 Invoice!");
		
		int invoiceItem_id = M_ProductSubcon2_ID;
		
		//2nd subcontractor Purchase		
		BigDecimal purchasePrice = priceSubcon2;		
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BPartnerSubcon2_ID, get_TrxName());
		
		//Service Receipt Header		
		TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
		inout.materialReceipt = false;
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		inout.setIsSOTrx(false);
		inout.setC_DocType_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getMaterialReceipt_DocType_ID());
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);		
		inout.setDateAcct(getDateAcct());	
		inout.setMovementDate(getDateAcct());		
		inout.setC_BPartner_ID(bp.getC_BPartner_ID());
		inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
		inout.setAD_User_ID(bp.getAD_User_ID());
		inout.setM_Warehouse_ID(getM_Warehouse_ID());
		inout.setPriorityRule(TF_MInOut.PRIORITYRULE_Medium);
		inout.setFreightCostRule(TF_MInOut.FREIGHTCOSTRULE_FreightIncluded);
		inout.saveEx(get_TrxName());
		
		//Material Receipt Line
		TF_MInOutLine ioLine = new TF_MInOutLine(inout);
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		
		ioLine.setLine(10);
		ioLine.setM_Product_ID(invoiceItem_id);
		ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		
		BigDecimal qty = getQtyReceived();		 
		
		ioLine.setQty(qty);
		ioLine.setC_UOM_ID(getC_UOM_ID());
		ioLine.set_ValueOfColumn("Price", purchasePrice);
		if(getC_Project_ID() > 0) {
			TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
			ioLine.setC_Tax_ID(proj.getC_Tax_ID());
		}
		ioLine.set_ValueOfColumn("DocStatus", "CO");
		ioLine.saveEx(get_TrxName());
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
		
		inout.saveEx();
	
	}

	public void createTransportMaterialReceipt() {
		
		
		MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
				
		if(we.getTF_RentedVehicle_ID() == 0)
			return;
		
		MRentedVehicle rv = new MRentedVehicle(getCtx(), we.getTF_RentedVehicle_ID(), get_TrxName());
		if(rv.isOwnVehicle() || !rv.isTransporter())
			return;
		
		//Don't Create Material Receipt for the same Transporter
		//It stops the recursive loop
		if(rv.getC_BPartner_ID() == getSubcontractor_ID())
			return;
		
				
		MDestination dest = new MDestination(getCtx(), we.getTF_Destination_ID(), get_TrxName());
		TF_MBPartner bp = new TF_MBPartner(getCtx(), rv.getC_BPartner_ID(), get_TrxName());
		
		TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
		inout.materialReceipt = false;
		inout.setTF_WeighmentEntry_ID(getTF_WeighmentEntry_ID());		
		inout.setIsSOTrx(false);
		inout.setC_DocType_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getMaterialReceipt_DocType_ID());
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);		
		inout.setDateAcct(getDateAcct());	
		inout.setMovementDate(getDateAcct());
		inout.setC_BPartner_ID(rv.getC_BPartner_ID());
		inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
		inout.setAD_User_ID(bp.getAD_User_ID());
		inout.setM_Warehouse_ID(getM_Warehouse_ID());
		inout.setPriorityRule(TF_MInOut.PRIORITYRULE_Medium);
		inout.setFreightCostRule(TF_MInOut.FREIGHTCOSTRULE_FreightIncluded);
		inout.saveEx(get_TrxName());
		
		//Material Receipt Line
		TF_MInOutLine ioLine = new TF_MInOutLine(inout);
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		
		ioLine.setLine(10);
		ioLine.setM_Product_ID(rv.getM_Product_ID());
		ioLine.setM_Locator_ID(wh.getDefaultLocator().get_ID());
			
		
		BigDecimal qty = BigDecimal.ZERO;
		BigDecimal price = BigDecimal.ZERO;		
		
		int Vendor_ID = rv.getC_BPartner_ID();
		
		MLumpSumRentConfig lumpsumConfig = MLumpSumRentConfig.getFreightConfig(getCtx(), we.getAD_Org_ID(), Vendor_ID, we.getC_BPartner_ID(), we.getM_Product_ID(), 
				we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
				
		int Load_UOM_ID = MSysConfig.getIntValue("LOAD_UOM", 1000072, we.getAD_Client_ID());
		int KM_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, we.getAD_Client_ID());
		int MT_KM_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000071, we.getAD_Client_ID());
		int Rent_UOM_ID = 0;
		int TF_LumpSumRentConfig_ID = 0;
		BigDecimal RentMargin = BigDecimal.ZERO;		
		BigDecimal distance = dest.getDistance();
							
						
		BigDecimal RateMTKM = BigDecimal.ZERO;				
		
		
		if(lumpsumConfig != null) {
			//ioLine.set_ValueOfColumn("FreightRule", we.getFreightRule());
			price = lumpsumConfig.getCustomerFreightPrice(we.getC_BPartner_ID());
			
			if(lumpsumConfig.getC_UOM_ID() == Load_UOM_ID)
			{
				Rent_UOM_ID = Load_UOM_ID;
				qty = BigDecimal.ONE;
				
			}
			else if(lumpsumConfig.getC_UOM_ID() == KM_UOM_ID)
			{
				Rent_UOM_ID = KM_UOM_ID;
				qty = dest.getDistance();									
			}
			else if(lumpsumConfig.getC_UOM_ID() == MT_KM_UOM_ID)
			{
				Rent_UOM_ID = MT_KM_UOM_ID;
				qty = we.getMT();									
				RateMTKM = price;
			}
			else
			{
				Rent_UOM_ID = lumpsumConfig.getC_UOM_ID();
				qty = we.getNetWeightUnit();									
			}
			TF_LumpSumRentConfig_ID = lumpsumConfig.getTF_LumpSumRent_Config_ID();
			RentMargin = (BigDecimal) lumpsumConfig.getCustomerFreightMargin(we.getC_BPartner_ID());
			
			we.setTF_LumpSumRent_Config_ID(TF_LumpSumRentConfig_ID);	
			we.saveEx(get_TrxName());
		}
		else {
			Rent_UOM_ID = Load_UOM_ID;
			qty = BigDecimal.ONE;
			price = BigDecimal.ZERO;
		}
		
				 
		
		ioLine.setM_Product_ID(rv.getM_Product_ID());
		ioLine.setC_UOM_ID(Rent_UOM_ID);
		ioLine.setTF_Destination_ID(we.getTF_Destination_ID());
		ioLine.setDistance(distance);
		ioLine.setRateMTKM(RateMTKM);
		ioLine.setQty(qty);
		ioLine.set_ValueOfColumn("Price", price);
		ioLine.setTF_LumpSumRent_Config_ID(TF_LumpSumRentConfig_ID);
		ioLine.setRentMargin(RentMargin);
		ioLine.setM_Locator_ID(qty);
		ioLine.setDescription("Destination : " + dest.getName());		
		ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_Unbilled);
		//put transporter freight charge without margin.
		if(TF_LumpSumRentConfig_ID > 0) {			
			price = lumpsumConfig.getFreightPrice();			
			ioLine.setC_Tax_ID(lumpsumConfig.getC_Tax_ID());
			ioLine.setIsTaxIncluded(lumpsumConfig.isTaxIncluded());
			ioLine.set_ValueOfColumn("TF_LumpSumRent_Config_ID", lumpsumConfig.getTF_LumpSumRent_Config_ID());
		}		
		ioLine.set_ValueOfColumn("DocStatus", "CO");
		ioLine.saveEx(get_TrxName());
		
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + inout.getProcessMsg());
		
		inout.saveEx();
	}
	
	public void reverseServiceReceipts() {
						
		String whereClause = "TF_WeighmentEntry_ID = ? AND MovementType = ? AND DocStatus = 'CO'";
		List<TF_MInOut> list = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID(), MInOut.MOVEMENTTYPE_VendorReceipts)
				.list();
		for(TF_MInOut io : list) {
			if(io.getDocStatus().equals(DOCSTATUS_Completed))
				io.reverseCorrectIt();
			io.saveEx();
		}
		
	}
	
}
