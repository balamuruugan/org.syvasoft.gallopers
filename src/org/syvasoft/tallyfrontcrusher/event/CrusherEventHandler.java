package org.syvasoft.tallyfrontcrusher.event;

import java.math.BigDecimal;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.InvoiceFullyMatchedException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MSysConfig;
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
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;


public class CrusherEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(CrusherEventHandler.class);
	@Override
	protected void initialize() {
		//Document Events
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, TF_MInvoice.Table_Name);		
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MProduction.Table_Name);
				

	}

	@Override
	protected void doHandleEvent(Event event) {
		PO po = getPO(event);
		if(po.get_TableName().equals(MInvoice.Table_Name)) {
			
			//NOTE::Do not create another Invoice instance based on this RecordID and use it to generate receipts
			//will lead to deadlock...
			MInvoice inv = (MInvoice) po;
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)) {				
				
				postJobworkExpenseVarianceJournal(inv);				
				
				// AP Invoice with Material Receipt
				// Generate the MR immediately.
				//if(inv.getC_DocTypeTarget_ID() == 1000051)
				//	generateReceiptFromInvoice(inv);
				
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

}