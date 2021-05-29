package org.syvasoft.tallyfrontcrusher.event;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MMatchInv;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MRole;
import org.compiere.model.MSequence;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTransaction;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.RollUpCosts;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.osgi.service.event.Event;
import org.syvasoft.tallyfrontcrusher.model.MAdditionalTransactionSetup;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCashCounter;
import org.syvasoft.tallyfrontcrusher.model.MCounterTransactionSetup;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.MJobworkItemIssue;
import org.syvasoft.tallyfrontcrusher.model.MTyre;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;
import org.syvasoft.tallyfrontcrusher.model.TF_MCharge;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MJournal;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrg;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;


public class CrusherEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(CrusherEventHandler.class);
	@Override
	protected void initialize() {
		//Document Events
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, TF_MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT, TF_MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MProduction.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPayment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MPayment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MTransaction.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MTyre.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MJournal.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MMatchInv.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInOutLine.Table_Name);
		registerEvent(IEventTopics.AFTER_LOGIN);		

	}
	
	@Override
	protected void doHandleEvent(Event event) {
		if(event.getTopic().equals(IEventTopics.AFTER_LOGIN)) {
			String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
			Env.setContext(Env.getCtx(), "#DieselIssue", dieselIssue);
			String sql = "SELECT C_Element_ID FROM C_Element WHERE AD_Client_ID =? AND ElementType='A'";
			int COA_ID = DB.getSQLValue(null, sql, Env.getAD_Client_ID(Env.getCtx()));
			Env.setContext(Env.getCtx(), "#C_Element_ID", COA_ID);
			TF_MOrg org = new TF_MOrg(Env.getCtx(), Env.getAD_Org_ID(Env.getCtx()), null);
			Env.setContext(Env.getCtx(), "#OrgType", org.getOrgType());			
			return;
		}
		
		PO po = getPO(event);
		if(po.get_TableName().equals(MPayment.Table_Name)) {
			MPayment payment = (MPayment) po;
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				//To show party name and phone for cash sales...
				if(payment.getC_Invoice_ID() > 0 && payment.getReversal_ID() == 0) {
					MInvoice inv =  (MInvoice) payment.getC_Invoice();
					if(inv.getDescription() != null) {
						payment.addDescription(inv.getDescription());
					}
					
					
					// Set Default Cash Account
					if(inv.getC_Order_ID() > 0 && inv.getPaymentRule().equals(MInvoice.PAYMENTRULE_Cash)) {
						TF_MOrder ord = new TF_MOrder(inv.getCtx(), inv.getC_Order_ID(), inv.get_TrxName());
						MWeighmentEntry we = new MWeighmentEntry(inv.getCtx(), ord.getTF_WeighmentEntry_ID(), inv.get_TrxName());
						if(ord.getC_BankAccount_ID() > 0) 
							payment.setC_BankAccount_ID(ord.getC_BankAccount_ID());												
						}						
					}
				

			  if(payment.is_new() && payment.get_ValueAsInt("TF_CashCounter_ID")==0) {
				 
					String Where=" IsDefault='Y'";

					MCashCounter cashCounter= new Query(payment.getCtx(),MCashCounter.Table_Name , Where, payment.get_TrxName())
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.first();
					if(cashCounter!=null) {
						payment.set_ValueNoCheck("TF_CashCounter_ID",cashCounter.getTF_CashCounter_ID());
					}
				}

			}
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE)) {
				if(payment.getC_Invoice_ID() > 0) {
					if(payment.getC_DocType().isSOTrx())
						payment.set_ValueOfColumn(TF_MPayment.COLUMNNAME_CashType, TF_MPayment.CASHTYPE_CustomerPayment);					
					else
						payment.set_ValueOfColumn(TF_MPayment.COLUMNNAME_CashType, TF_MPayment.CASHTYPE_VendorPayment);
					
					payment.set_ValueOfColumn(TF_MPayment.COLUMNNAME_TF_BPartner_ID, payment.getC_BPartner_ID());					
				}
				if(!payment.getDocStatus().equals(payment.DOCSTATUS_Reversed))
					return;
				boolean isOnAccount = payment.get_ValueAsBoolean("OnAccount");
				String documentNo2 = payment.get_ValueAsString("DocumentNo2");
				if(!isOnAccount || (isOnAccount && documentNo2 != null && documentNo2.length() > 0))
					return;
				
				MSequence seq = new Query(payment.getCtx(), MSequence.Table_Name, "Name='CashBook2'", payment.get_TrxName())
						.setClient_ID().first();
				if(seq == null)
					throw new AdempiereException("Create CashBook2 Doument Sequence!");
				
				String seqNo = MSequence.getDocumentNoFromSeq(seq, payment.get_TrxName(), payment);
				payment.set_ValueOfColumn("DocumentNo2", seqNo);;
			}			
			
		}
		else if(po.get_TableName().equals(MInvoice.Table_Name)) {
			
			//NOTE::Do not create another Invoice instance based on this RecordID and use it to generate receipts
			//will lead to deadlock...
			MInvoice inv = (MInvoice) po;
			TF_MInvoice srcInv = new TF_MInvoice(inv.getCtx(), inv.getC_Invoice_ID(), inv.get_TrxName());
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)) {				
				
				postJobworkExpenseVarianceJournal(inv);				
				//createAdditionalInvoice(srcInv);
				// AP Invoice with Material Receipt
				// Generate the MR immediately.
				//if(inv.getC_DocTypeTarget_ID() == 1000051)
				//	generateReceiptFromInvoice(inv);				
			}
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_REVERSECORRECT)) {
				//reverseAdditionalTransactions(srcInv);
			}
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				if (inv.getC_Order_ID() > 0) {
					TF_MOrder ord = new TF_MOrder(Env.getCtx(), inv.getC_Order_ID(), inv.get_TrxName());
					inv.set_ValueOfColumn(TF_MOrder.COLUMNNAME_VehicleNo, ord.getVehicleNo());
				}
			}
		}
		else if(po.get_TableName().equals(MInvoiceLine.Table_Name)) {
			MInvoiceLine iLine = (MInvoiceLine) po;
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				if (iLine.getC_OrderLine_ID() > 0) {
					TF_MOrderLine oLine = new TF_MOrderLine(Env.getCtx(), iLine.getC_OrderLine_ID(), iLine.get_TrxName());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_SandType, oLine.getSandType());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_BucketQty, oLine.getBucketQty());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_IsPermitSales, oLine.isPermitSales());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TonePerBucket, oLine.getTonePerBucket());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_BucketRate, oLine.getBucketRate());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TotalLoad, oLine.getTotalLoad());
					iLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TF_VehicleType_ID, oLine.getTF_VehicleType_ID() == 0 ? null : oLine.getTF_VehicleType_ID());
				}
			}
		}
		else if(po.get_TableName().equals(MInOutLine.Table_Name)) {
			MInOutLine iLine = (MInOutLine) po;
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				if (iLine.getC_OrderLine_ID() > 0) {
					TF_MOrderLine oLine = new TF_MOrderLine(Env.getCtx(), iLine.getC_OrderLine_ID(), iLine.get_TrxName());
					int M_Warehouse_ID = oLine.get_ValueAsInt("M_WarehouseNew_ID");
					if(M_Warehouse_ID > 0)
						iLine.set_ValueOfColumn("M_WarehouseNew_ID", oLine.get_ValueAsInt("M_WarehouseNew_ID"));
					
					int M_Locator_ID = oLine.get_ValueAsInt("M_Locator_ID");
					if(M_Locator_ID > 0)
						iLine.set_ValueOfColumn("M_Locator_ID", oLine.get_ValueAsInt("M_Locator_ID"));					
				}
			}
		}
		else if(po instanceof MOrder) {
			MOrder ord = (MOrder) po;
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)) {
				createDriverTipsPayment(ord);
				MJobworkItemIssue.IssueFromPO(ord);
			}
			else if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				int ref_Order_ID = ord.getRef_Order_ID();
				if(ref_Order_ID > 0) {
					MCounterTransactionSetup ctransSetup = new MCounterTransactionSetup(ord.getCtx(), 0, null);
					//Set Counter Warehouse
					MOrgInfo o = MOrgInfo.get(ord.getCtx(), ord.getAD_Org_ID(), null);
					ord.setM_Warehouse_ID(o.getM_Warehouse_ID());
					
					//Set Counter Product and Locators					
					int src_Product_ID = ord.get_ValueAsInt(TF_MOrder.COLUMNNAME_Item1_ID);
					if(src_Product_ID > 0) {
						int M_Product_ID = ctransSetup.getCounterProduct_ID(ord.getAD_Org_ID(), src_Product_ID);					
						ord.set_ValueOfColumn(TF_MOrder.COLUMNNAME_Item1_ID, M_Product_ID);					
						MWarehouse wh = (MWarehouse) o.getM_Warehouse();
						ord.set_ValueOfColumn(TF_MOrder.COLUMNNAME_M_Locator_ID, wh.getDefaultLocator().getM_Locator_ID());
					}
				}
			}
		}
		else if (po instanceof MOrderLine) {
			MOrderLine oLine = (MOrderLine) po;
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) {
				int ref_OrderLine_ID = oLine.getRef_OrderLine_ID();
				if(ref_OrderLine_ID > 0) {
					//Set Counter warehouse
					MOrgInfo o = MOrgInfo.get(oLine.getCtx(), oLine.getAD_Org_ID(), null);
					oLine.setM_Warehouse_ID(o.getM_Warehouse_ID());
					
					//Counter Product and Locators
					MCounterTransactionSetup ctransSetup = new MCounterTransactionSetup(oLine.getCtx(), 0, null);
					int src_Product_ID = oLine.get_ValueAsInt(MOrderLine.COLUMNNAME_M_Product_ID);
					int M_Product_ID = ctransSetup.getCounterProduct_ID(oLine.getAD_Org_ID(), src_Product_ID);
					oLine.setM_Product_ID(M_Product_ID);					
					MWarehouse wh = (MWarehouse) o.getM_Warehouse();
					oLine.set_ValueOfColumn(TF_MOrder.COLUMNNAME_M_Locator_ID, wh.getDefaultLocator().getM_Locator_ID());
				}
			}
		}
		else if (po instanceof MProduction) {
			MProduction prod = (MProduction) po;
			//Before Prepare		
			//1. Create Costing Record for the End Product if there is no cost.
			//2. Update Cost using Rollup BOM Cost Process.
			int product_ID 			= 	prod.getM_Product_ID();
			MAcctSchema as 			= 	MClient.get(prod.getCtx()).getAcctSchema();
			MProduct product		=	MProduct.get(prod.getCtx(), product_ID);
			String costingMethod 	= 	product.getCostingMethod(as) ;
			int costElement_ID 		= 	MCostElement.getMaterialCostElement(prod.getCtx(), costingMethod).get_ID();
			
			String localTrxName		=	prod.get_TrxName();
			Trx trx					=	Trx.get(localTrxName, true);
			try {
				MCost cost				=	MCost.get(product, 0, as, 0, costElement_ID, localTrxName);
				boolean alwaysUpdateStdCost = MSysConfig.getBooleanValue("MFG_UpdateCostsOnCreate", false, prod.getAD_Client_ID());
				if(cost.getCurrentCostPrice().doubleValue() != 0 && !alwaysUpdateStdCost) {
					return;
				}
				cost.saveEx();
				//trx.commit();
							
					
				// Call Rollup BOM Cost process
				
				// Create instance parameters. I e the parameters you want to send to the process.
				ProcessInfoParameter pi1 = new ProcessInfoParameter("M_Product_Category_ID", 0,"","","");
				ProcessInfoParameter pi2 = new ProcessInfoParameter("M_Product_ID", product_ID, "","","");
				ProcessInfoParameter pi3 = new ProcessInfoParameter("M_CostElement_ID", costElement_ID, "","","");
	
				// Lookup process in the AD, in this case by value
				MProcess pr = new Query(Env.getCtx(), MProcess.Table_Name, "value=?", null)
				                        .setParameters("M_Product_BOM_Rollup")
				                        .first();
				if (pr==null) {
				      log.warning("Process [RollUp BOM Cost] does not exist. ");
				      return;
				}
	
				// Create a process info instance. This is a composite class containing the parameters.
				ProcessInfo pi = new ProcessInfo("", pr.get_ID(),0,0);
				pi.setParameter(new ProcessInfoParameter[] {pi1, pi2, pi3});
				
				// Create an instance of the actual process class.
				RollUpCosts process = new RollUpCosts();				
				
				// Create process instance (mainly for logging/sync purpose)
				MPInstance mpi = new MPInstance(Env.getCtx(), 0, null);				
				mpi.setAD_Process_ID(pr.get_ID());				
				mpi.setRecord_ID(0);
				mpi.save();
				mpi.createParameter(10, pi1.getParameterName(), pi1.getParameterAsInt());
				mpi.createParameter(20, pi2.getParameterName(), pi2.getParameterAsInt());
				mpi.createParameter(30, pi3.getParameterName(), pi3.getParameterAsInt());
				mpi.save();
	
				// Connect the process to the process instance.
				pi.setAD_PInstance_ID(mpi.get_ID());
	
				log.info("Starting process " + pr.getName());
				boolean result = process.startProcess(prod.getCtx(), pi, trx);
				//trx.commit();
			}
			catch(Exception ex) {
				//trx.rollback();
				throw ex;
			}
			finally {
				//trx.close();
			}
			
			// End Call Rollup BOM Cost process
			
		}
		else if(po instanceof MTransaction) {			
			MTransaction trans = (MTransaction) po;
			if(IEventTopics.PO_BEFORE_NEW.equals(event.getTopic())) {
				//Set Opening Qty at Locator Level
				BigDecimal onHandQty = MStorageOnHand.getQtyOnHandForLocator(trans.getM_Product_ID(), trans.getM_Locator_ID(), 
						trans.getM_AttributeSetInstance_ID(), null);
				trans.set_ValueOfColumn("Opening_Qty", onHandQty);
			}
		}
		else if (po instanceof MTyre) {
			MTyre tyre = (MTyre) po;
			if(IEventTopics.PO_AFTER_NEW.equals(event.getTopic())) {
				MTyre.createTyreLifeRecords(tyre);				
			}
		}
		else if (po instanceof MJournal || po.get_TableName().equals(MJournal.Table_Name) ) {
			MJournal j = (MJournal) po;
			
			if(j.getReversal_ID() > 0 && IEventTopics.PO_BEFORE_NEW.equals(event.getTopic())) {
				MJournal revJ =  new MJournal(j.getCtx(), j.getReversal_ID(), j.get_TrxName());			
				int C_Project_ID = revJ.get_ValueAsInt("C_Project_ID");
				if(C_Project_ID > 0)
					j.set_ValueOfColumn("C_Project_ID", C_Project_ID);
			}
		}
		else if (po instanceof MMatchInv) {
			MMatchInv inv = (MMatchInv) po;
			
			if(IEventTopics.PO_BEFORE_NEW.equals(event.getTopic())) {
				MInOutLine ioLine = new MInOutLine(inv.getCtx(), inv.getM_InOutLine_ID(), inv.get_TrxName());
				if(inv.getC_InvoiceLine().getC_OrderLine_ID() > 0) 
					ioLine.setC_OrderLine_ID(inv.getC_InvoiceLine().getC_OrderLine_ID());
				ioLine.saveEx();
			}
		}
	}
	
	private void postJobworkExpenseVarianceJournal(MInvoice inv) {		
		MInvoiceLine[] lines = inv.getLines();
		
		//Post Jobwork Expense Variance Journal for Subcontractor Invoice
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(inv.getCtx());
		for(MInvoiceLine line : lines) {
			if(line.getM_Product_ID() == glConfig.getJobWork_Product_ID()) {
				MBoulderReceipt.postJobworkExpenseVarianceJournal(inv.getCtx(), inv, line.getPriceEntered(), inv.get_TrxName());
				break;
			}
		}
	}
	
	private void createDriverTipsPayment(MOrder ord) {
		if(ord.get_Value(TF_MOrder.COLUMNNAME_DriverTips) == null)
			return;
		BigDecimal amt = (BigDecimal) ord.get_Value(TF_MOrder.COLUMNNAME_DriverTips);
		if(amt.doubleValue() == 0)
			return;
		
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(ord.getCtx());
		
		//Create Driver Tips Charge if it is not there already.
		//It should be in atomic transaction to get account settings of Charge for the current docaction transaction.
		TF_MCharge charge = TF_MCharge.createChargeFromAccount(ord.getCtx(), glConfig.getTipsExpenseAcct_ID(), null);
		
		int TF_VehicleType_ID = ord.get_ValueAsInt("Item1_VehicleType_ID");
		
		MVehicleType vtype=new MVehicleType(ord.getCtx(), TF_VehicleType_ID, ord.get_TrxName());

		//Get Invoice Document no
		String Where=" C_Order_ID = ? AND DocStatus = 'CO'";
		
		MInvoice inv = new Query(ord.getCtx(), MInvoice.Table_Name,Where, ord.get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(ord.getC_Order_ID())
				.first();
		
		String invoiceNo="";
		if(inv!=null) {
			invoiceNo=inv.getDocumentNo();
		}
		
		int TF_Weighment_ID = ord.get_ValueAsInt("TF_WeighmentEntry_ID");
		MWeighmentEntry we = new MWeighmentEntry(ord.getCtx(), TF_Weighment_ID, null);
		invoiceNo = we.getDocumentNo();
		//Posting Payment Document for Driver Tips
		TF_MPayment payment = new TF_MPayment(ord.getCtx(), 0, ord.get_TrxName());
		payment.setAD_Org_ID(ord.getAD_Org_ID());
		payment.setDateAcct(ord.getDateAcct());
		payment.setDateTrx(ord.getDateAcct());
		payment.setDescription("DRIVER BETA AMOUNT GIVEN FOR DC:# "+ invoiceNo +", Vehicle Type : "+vtype.getName());
		//* Commented for Laxmi Stone */
		//payment.setCashType(TF_MPayment.CASHTYPE_GeneralExpense);
		payment.setC_DocType_ID(false);		
		payment.setC_Charge_ID(charge.getC_Charge_ID());
		payment.setUser1_ID(ord.getUser1_ID()); // Profit Center
		payment.setC_ElementValue_ID(glConfig.getTipsExpenseAcct_ID());
		
		payment.setC_BankAccount_ID(TF_MBankAccount.getDefaultCashAccount(ord.getCtx(), ord.getAD_Org_ID(), null));
		
		MUser user = MUser.get(ord.getCtx(), Env.getAD_User_ID(ord.getCtx()));
		payment.setC_BPartner_ID(user.getC_BPartner_ID());
		payment.setPayAmt(amt);
		//payment.setC_Currency_ID(Env.getContextAsInt(ord.getCtx(), "$C_Currency_ID"));
		payment.setC_Currency_ID(ord.getC_Currency_ID());
		payment.setDocStatus(TF_MOrder.DOCSTATUS_InProgress);
		payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
		payment.saveEx();
		payment.processIt(DocAction.ACTION_Complete);
		payment.saveEx();
		
		ord.set_ValueOfColumn(TF_MOrder.COLUMNNAME_TF_DriverTips_Pay_ID, payment.getC_Payment_ID());
		ord.saveEx();
		
	}
	
	private void generateReceiptFromInvoice(MInvoice invoice) {
		//		
		if (invoice.get_ID() <= 0)
			throw new AdempiereException("@NotFound@ @C_Invoice_ID@");		
		
		int M_Warehouse_ID = invoice.get_ValueAsInt("M_Warehouse_ID");
		if(M_Warehouse_ID == 0)
			throw new AdempiereException("Invalid Warehouse!");
		
		MInOut m_inout = new MInOut (invoice, 0, null, M_Warehouse_ID);
		m_inout.save();
		
		//
		for (MInvoiceLine invoiceLine : invoice.getLines(false))
		{
			BigDecimal qtyMatched = invoiceLine.getMatchedQty();
			BigDecimal qtyInvoiced = invoiceLine.getQtyInvoiced();
			BigDecimal qtyNotMatched = qtyInvoiced.subtract(qtyMatched);
			// If is fully matched don't create anything
			if (qtyNotMatched.signum() == 0)
			{
				break;
			}			
			MInOutLine sLine = new MInOutLine(m_inout);
			sLine.setInvoiceLine(invoiceLine, 0,	//	Locator 
				invoice.isSOTrx() ? qtyNotMatched : Env.ZERO);
			sLine.setQtyEntered(qtyNotMatched);
			sLine.setMovementQty(qtyNotMatched);
			if (invoice.isCreditMemo())
			{
				sLine.setQtyEntered(sLine.getQtyEntered().negate());
				sLine.setMovementQty(sLine.getMovementQty().negate());
			}
			sLine.saveEx();
			//
			invoiceLine.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
			invoiceLine.saveEx();
			
			//Update Costing for products.
			MCostDetail.createInvoice(MClient.get(invoice.getCtx()).getAcctSchema(), invoice.getAD_Org_ID(), invoiceLine.getM_Product_ID(), 0, invoiceLine.getC_InvoiceLine_ID()
			, 0, invoiceLine.getPriceEntered().multiply(qtyInvoiced) , qtyInvoiced, invoiceLine.getDescription(), invoice.get_TrxName());
		}
		
		// added AdempiereException
		if (!m_inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + m_inout.getProcessMsg());
	}
	
	public void createAdditionalInvoice(TF_MInvoice srcInv) {
		String weighmentNo = null;
		if(srcInv.getC_Order_ID() > 0) {
			TF_MOrder ord = new TF_MOrder(srcInv.getCtx(), srcInv.getC_Order_ID(), srcInv.get_TrxName());
			if(ord.getTF_WeighmentEntry_ID() > 0) {
				MWeighmentEntry wEntry = new MWeighmentEntry(srcInv.getCtx(), ord.getTF_WeighmentEntry_ID(), srcInv.get_TrxName());
				weighmentNo = wEntry.getDocumentNo();
			}
		}
		for (MInvoiceLine srcLine : srcInv.getLines()) {
			
			List<MAdditionalTransactionSetup> ctransSetups = MAdditionalTransactionSetup.getAdditionalTransaction
					(srcInv.getCtx(), srcInv.getAD_Org_ID(), srcInv.getC_DocTypeTarget_ID(), srcInv.getC_BPartner_ID(), srcLine.getM_Product_ID());
			for(MAdditionalTransactionSetup ctransSetup : ctransSetups) {
				
				TF_MBPartner bp = new TF_MBPartner(srcInv.getCtx(), ctransSetup.getTo_Bpartner_ID(), srcInv.get_TrxName());
				
				//Invoice Header
				TF_MInvoice invoice = new TF_MInvoice(srcInv.getCtx(), 0, srcInv.get_TrxName());
				invoice.setClientOrg(srcInv.getAD_Client_ID(), ctransSetup.getTo_Org_ID());
				invoice.setC_DocTypeTarget_ID(ctransSetup.getTo_Doctype_ID());	// Counter Doc
				invoice.setIsSOTrx(ctransSetup.getTo_Doctype().isSOTrx());
				invoice.setDateInvoiced(srcInv.getDateInvoiced());
				invoice.setDateAcct(srcInv.getDateAcct());
				//
				invoice.setSalesRep_ID(Env.getAD_User_ID(srcInv.getCtx()));		
				//
				
				invoice.setBPartner(bp);				
				invoice.setVehicleNo(srcInv.getVehicleNo());
				if(weighmentNo != null)
					invoice.setDescription("Ticket No: " + weighmentNo);
				else
					invoice.addDescription("Ref Invoice: " + srcInv.getDocumentNo());
				invoice.addDescription(srcInv.getDescription());
				
				//Price List
				int m_M_PriceList_ID = Env.getContextAsInt(srcInv.getCtx(), "#M_PriceList_ID");
				
				if(!srcInv.isSOTrx() && bp.getM_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getM_PriceList_ID();
				else if(srcInv.isSOTrx() && bp.getPO_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getPO_PriceList_ID();
				
				invoice.setM_PriceList_ID(m_M_PriceList_ID);
				invoice.setC_Currency_ID(MPriceList.get(srcInv.getCtx(), m_M_PriceList_ID, srcInv.get_TrxName()).getC_Currency_ID());
				
				//Financial Dimension - Profit Center		
				//invoice.setC_Project_ID(counterProj.getC_Project_ID());
				invoice.setRef_Invoice_ID(srcInv.getC_Invoice_ID());
				invoice.saveEx();
				
				//Create Invoice Line
				MInvoiceLine invLine = new MInvoiceLine(invoice);				
				invLine.setM_Product_ID(ctransSetup.getTo_Product_ID(), true);
				invLine.setQty(srcLine.getQtyInvoiced().multiply(ctransSetup.getToQtyRatio().setScale(2, RoundingMode.HALF_EVEN)));
				invLine.setC_UOM_ID(ctransSetup.getToUom_ID() > 0 ? ctransSetup.getToUom_ID() : srcLine.getC_UOM_ID());
				BigDecimal price = ctransSetup.getToUnitPriceRatio().multiply(srcLine.getPriceEntered()).setScale(2, RoundingMode.HALF_EVEN);
				if(ctransSetup.getToUnitPrice().doubleValue() > 0) {
					price = ctransSetup.getToUnitPrice();
				}
				invLine.setPriceActual(price);
				invLine.setPriceList(price);
				invLine.setPriceLimit(price);
				invLine.setPriceEntered(price);				
				invLine.setC_Tax_ID(srcLine.getC_Tax_ID());
				invLine.setDescription(srcLine.getDescription());				
				invLine.saveEx();				
				
				//Invoice DocAction
				if (!invoice.processIt(DocAction.ACTION_Complete))
					throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
				invoice.saveEx();
								
			}
		}
	}
	
	private void reverseAdditionalTransactions(TF_MInvoice srcInv) {
		String whereClause = "Ref_Invoice_ID = ? AND DocStatus = 'CO'";
		List<TF_MInvoice> refInvoices = new Query(srcInv.getCtx(), TF_MInvoice.Table_Name, whereClause, srcInv.get_TrxName())
				.setClient_ID()
				.setParameters(srcInv.getC_Invoice_ID())
				.list();
		for(TF_MInvoice inv : refInvoices) {			
			if(inv.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				inv.reverseCorrectIt();
				inv.saveEx();
			}
		}
	}

}
