--

SET SEARCH_PATH TO adempiere;

--SELECT * FROM C_Order WHERE ad_client_id=1000000;

-- !!!dangerous!!! This scripts deletes ADempiere and iDempiere transactional data. 
-- This script is useful when you need to prepare for go-live and you need to delete all your test data.
-- Be aware this updates all clients including GardenWorld
-- If you are running this script against a database with lots of transactions,
--   it may take a while. Below is a link to creating drop and restore constraints commands that greatly improve speed of this script.
--   http://www.chuckboecking.com/blog/bid/196810/Data-Migration-in-PostgreSQL-and-ADemipere-Open-Source-ERP
-- This script is designed for iDempiere. Be aware that ADempiere and iDempiere differ slightly. You may need to make small changes to accomodate ADempiere. 

-- To run this script from the command line, use this command:
-- sudo -u postgres psql -f chuboe_delete_transactional_data.sql -d idempiere

-- thanks to Rumman to improving the script!!

-- FYI - Carlos created a SQL script to completely remove a client:
-- https://bitbucket.org/CarlosRuiz_globalqss/idempiere-stuff/src/stuff/DeleteAdempiereClient_pg.SQL

set search_path to adempiere;

-- The below will help you limit what orgs you clean
-- ENHANCEMENT COMMENT BEGIN

	-- uncomment this statement
	-- CREATE TABLE IF NOT EXISTS chuboe_org_preserve (
	--     ad_org_id numeric
	-- );

	-- uncomment this statement - this and the next statement ensure a -1 entry exists before each script execution. You do not want this table to return null.
	-- delete from chuboe_org_preserve
	-- where ad_org_id = -1;

	-- uncomment this statement - see comments for previous statement.
	-- insert into chuboe_org_preserve
	-- values (-1);
	
	-- Add any ord_id to chuboe_org_preserve you wish to preserve. You do not need to do this every time the script executes.

	-- update all statements in the this file to include a where statement like this:
	-- delete from ad_changelog where ad_client_id not in (select ad_org_id from chuboe_org_preserve);

-- ENHANCEMENT COMMENT END

-- Crusher Tables
UPDATE TF_Boulder_Receipt SET Subcon_Invoice_ID = NULL, TF_Employee_Salary_ID = NULL, TF_Vehicle_Rent_ID = NULL,
	TF_Quarry_Rent_ID = NULL;
UPDATE TF_Employee_Salary SET GL_Journal_ID = NULL;
UPDATE TF_Vehicle_Rent SET GL_Journal_ID = NULL;
UPDATE TF_Quarry_Rent SET GL_Journal_ID = NULL;
UPDATE TF_Fuel_Issue SET GL_Journal_ID = NULL;
UPDATE TF_Labour_Wage SET GL_Journal_ID = NULL;
UPDATE TF_Labour_Wage_Advance SET C_Payment_ID = NULL;
UPDATE TF_Employee_Salary_Advance SET C_Payment_ID = NULL;
UPDATE TF_Labour_Wage_Issue SET GL_Journal_ID = NULL;
UPDATE TF_Labour_Wage_Issue SET C_Payment_ID = NULL;

UPDATE TF_Employee_Salary_Issue SET GL_Journal_ID = NULL;
UPDATE TF_Employee_Salary_Issue SET C_Payment_ID = NULL;

UPDATE TF_TripSheet SET TF_Vehicle_Rental_Contract_ID = NULL;
UPDATE C_Invoice SET TF_Vehicle_Rental_Contract_ID = NULL;

UPDATE M_Transaction SET TF_Boulder_Receipt_ID = NULL;

DELETE FROM TF_Boulder_Receipt_Line;
delete from m_costhistory WHERE ad_client_id=1000000;;
delete from m_costdetail WHERE ad_client_id=1000000;;
DELETE FROM TF_Boulder_Receipt;
DELETE FROM pm_meter_log;
DELETE FROM pm_spare;
DELETE FROM tf_tripsheet_am;
DELETE FROM tf_tripsheet_salary;
UPDATE tf_employee_salary SET tf_tripsheet_id = NULL;
UPDATE tf_tripsheet SET tf_employee_salary_id = NULL;
UPDATE tf_weighmententry SET tf_tripsheetproduct_id=NULL,tf_tripsheet_id=NULL;
DELETE FROM tf_tripsheetproduct;
DELETE FROM tf_drillingentry;
DELETE FROM pm_machinestmt;
DELETE FROM tf_tripsheet;



DELETE FROM TF_Employee_Salary;
DELETE FROM TF_Vehicle_Rent;
DELETE FROM TF_Quarry_Rent;
DELETE FROM TF_Fuel_Issue;

UPDATE tf_TripSheet SET tf_jobwork_issuedresource_id =NULL;
UPDATE TF_Crusher_Production SET TF_WeighmentEntry_id = NULL, Subcon_Invoice_ID = NULL, Subcon2_Invoice_ID =NULL;

--SubContract Tables
UPDATE M_InOut SET TF_WeighmentEntry_ID = NULL;
UPDATE C_Invoice SET TF_WeighmentEntry_ID = NULL;
UPDATE c_invoiceline SET TF_WeighmentEntry_ID = NULL;
UPDATE c_orderline SET TF_WeighmentEntry_ID = NULL;
UPDATE tf_crusherkatingentry SET TF_WeighmentEntry_ID = NULL;
DELETE FROM TF_Jobwork_Expense;
DELETE FROM TF_Jobwork_Charges;
DELETE FROM tf_jobwork_resrententry;
DELETE FROM TF_Jobwork_IssuedResource;
DELETE FROM TF_Jobwork_IssuedItems;
DELETE FROM TF_Jobwork_ReceivedItems;
DELETE FROM TF_Jobwork_ProductPrice;
DELETE FROM TF_Jobwork_ItemIssue;
delete from fact_acct WHERE ad_client_id=1000000;;
delete from fact_acct_summary WHERE ad_client_id=1000000;;
DELETE FROM c_recurring WHERE ad_client_id=1000000;;
UPDATE tf_jobwork_expense_entry set SubCon_Invoice_id = null, GL_Journal_ID = null;
DELETE FROM tf_jobwork_expense_entry WHERE AD_client_ID = 1000000;
UPDATE C_Payment SET EmpAdv_Journal_ID = null, C_Project_ID = null WHERE AD_client_ID = 1000000;
UPDATE TF_TripSheet SET SubCon_invoice_ID = null WHERE AD_client_ID = 1000000;;
UPDATE tf_coaopeningbalance SET GL_Journal_ID = null WHERE AD_client_ID = 1000000;;
UPDATE tf_investmentstructure SET GL_Journal_ID = null WHERE AD_client_ID = 1000000;;
UPDATE TF_InvestmentReceipt SET GL_Journal_ID = null WHERE AD_client_ID = 1000000;;
delete from gl_journalbatch WHERE ad_client_id=1000000 ;
delete from gl_journalline WHERE ad_client_id=1000000; 
UPDATE C_Order SET tf_trtaxinvoice_id = null ;
DELETE FROM tf_trtaxinvoiceLINE;
DELETE FROM tf_trtaxinvoice;
delete from gl_journal WHERE ad_client_id=1000000;; 
DELETE from c_validcombination WHERE C_Project_ID IN (SELECT C_Project_ID FROM C_Project WHERE C_Project_id !=1000000 AND ad_client_id=1000000);
UPDATE C_Invoice SET C_Project_ID = null WHERE AD_client_ID = 1000000;
UPDATE m_inventory SET C_Project_ID = null WHERE AD_Client_ID = 1000000;
DELETE FROM TF_TripSheet WHERE ad_client_id=1000000;
UPDATE C_Order SET tf_weighmententry_id = null, C_PROJECT_id = NULL, IssueToSubcontract_ID= null, SUBCON_invoice_id = null,
TransporterInvoice_ID = null
 WHERE AD_client_ID = 1000000;
 UPDATE tf_weighmententry SET tf_weighmententryprimary_id = NULL;
 DELETE FROM TF_CrusherKatingEntry;
DELETE FROM TF_Crusher_Production;
DELETE FROM TF_ImportSales;
DELETE FROM TF_Boulder_Movement;
DELETE FROM tf_rmsubcon_movement WHERE AD_client_ID = 1000000;
DELETE FROM tf_whatsapplog;
DELETE FROM TF_RMSubcon_Movement;
DELETE FROM TF_Boulder_Receipt;
DELETE FROM TF_Weighment_Permit;

--DELETE FROM  C_Project WHERE C_Project_id !=1000000 AND ad_client_id=1000000;;



-- End Crusher Tables.



--delete from ChuBoe_Replenish;
--delete from ChuBoe_Replenish_Product_PO;
--delete from chuboe_replenish_multiplier;

delete from ad_changelog WHERE ad_client_id=1000000;;
delete from c_allocationline WHERE ad_client_id=1000000;;
delete from c_allocationhdr WHERE ad_client_id=1000000;;
Update C_BankAccount Set CurrentBalance = 0 WHERE ad_client_id=1000000;;
delete from m_costhistory WHERE ad_client_id=1000000;;
delete from m_costdetail WHERE ad_client_id=1000000;;
delete from m_matchinv WHERE ad_client_id=1000000;;
delete from m_matchpo WHERE ad_client_id=1000000;;
delete from c_payselectionline WHERE ad_client_id=1000000;;
delete from c_payselectioncheck WHERE ad_client_id=1000000;;
delete from c_payselection WHERE ad_client_id=1000000;;


Update C_Invoice set C_Cashline_ID = null WHERE ad_client_id=1000000;;
Update C_Order set C_Cashline_ID = null WHERE ad_client_id=1000000;;
delete from C_Cashline WHERE ad_client_id=1000000;;
delete from C_Cash WHERE ad_client_id=1000000;; 
Update c_payment set C_Invoice_ID= null WHERE ad_client_id=1000000;;
delete from C_CommissionAmt WHERE ad_client_id=1000000;;
delete from C_CommissionDetail WHERE ad_client_id=1000000;;
delete from C_CommissionLine WHERE ad_client_id=1000000;;
delete from C_CommissionRun WHERE ad_client_id=1000000;;
delete from C_Commission WHERE ad_client_id=1000000;;
Delete from c_recurring_run WHERE ad_client_id=1000000;;
Delete from c_recurring WHERE ad_client_id=1000000;;
Delete from s_timeexpenseline WHERE ad_client_id=1000000;;
Delete from s_timeexpense WHERE ad_client_id=1000000;;
Delete from c_landedcostallocation WHERE ad_client_id=1000000;;
Delete from c_landedcost WHERE ad_client_id=1000000;;
delete from c_invoiceline WHERE ad_client_id=1000000;;
delete from c_invoicetax WHERE ad_client_id=1000000;;
delete from c_paymentallocate WHERE ad_client_id=1000000;;
delete from c_bankstatementline WHERE ad_client_id=1000000;;
delete from c_bankstatement WHERE ad_client_id=1000000;;
Update c_invoice set c_Payment_ID = null WHERE ad_client_id=1000000;;
Update c_order set c_Payment_ID= null, tf_drivertips_pay_id = null WHERE ad_client_id=1000000;;
Update c_order set c_opportunity_ID= null WHERE ad_client_id=1000000;;
delete from c_contactactivity WHERE ad_client_id=1000000;;
delete from c_opportunity WHERE ad_client_id=1000000;;
delete from c_depositbatchline WHERE ad_client_id=1000000;;
delete from c_depositbatch WHERE ad_client_id=1000000;;
delete from c_orderpayschedule WHERE ad_client_id=1000000;
delete from c_paymenttransaction WHERE ad_client_id=1000000;;
UPDATE c_bankaccount SET C_Payment_ID = null WHERE ad_client_id=1000000;;


DELETE FROM tf_investmentreceipt WHERE ad_client_id=1000000;;

delete from c_paymentbatch  WHERE ad_client_id=1000000;;
Update M_INOUTLINE Set C_Orderline_ID = null, M_RMALine_ID=null WHERE ad_client_id=1000000;;
Update M_INOUT Set C_Order_ID = null, C_Invoice_ID=null, M_RMA_ID=null WHERE ad_client_id=1000000;;
UPDATE C_Payment SET C_Invoice_ID = null WHERE ad_client_id=1000000;;
Update C_INVOICE Set M_RMA_ID = null WHERE ad_client_id=1000000;;
update R_Request set m_rma_id = null WHERE ad_client_id=1000000;;
delete from m_rmatax WHERE ad_client_id=1000000;;
delete from M_RMAline WHERE ad_client_id=1000000;;
delete from M_RMA WHERE ad_client_id=1000000;;

UPDATE C_BPartner SET C_Invoice_ID = null WHERE ad_Client_ID = 1000000;
UPDATE tf_jobwork_expense_entry set SubCon_Invoice_id = null;
UPDATE TF_TripSheet SET SubCon_invoice_ID = null;
delete from tf_bpopeningbalance WHERE ad_client_id=1000000;
UPDATE  C_Order SET TransporterInvoice_ID = NULL;

delete from PP_MRP WHERE ad_client_id=1000000;;
delete from m_requisitionline  WHERE ad_client_id=1000000;;
delete from m_requisition WHERE ad_client_id=1000000;;
update pp_order set c_orderline_id = null WHERE ad_client_id=1000000;;
UPDATE C_Order SET Item1_C_OrderLine_ID = NULL, Item2_C_OrderLine_ID = NULL,Vehicle_C_OrderLine_ID =null;
delete from c_orderline WHERE ad_client_id=1000000;;
delete from c_ordertax  WHERE ad_client_id=1000000;;
update r_request set c_order_id = null, M_inout_id = null WHERE ad_client_id=1000000;;
update r_requestaction set c_order_id = null, M_inout_id = null WHERE ad_client_id=1000000;;
DELETE FROM tf_rmsubcon_movement;

UPDATE tf_jobwork_expense_entry SET GL_Journal_ID = null;
delete from fact_acct WHERE ad_client_id=1000000;;
delete from fact_acct_summary WHERE ad_client_id=1000000;;
delete from gl_journalbatch WHERE ad_client_id=1000000;;
delete from gl_journalline WHERE ad_client_id=1000000;; 
delete from gl_journal WHERE ad_client_id=1000000;; 

UPDATE M_Product SET CostAdj_Inventory_ID = NULL, M_Inventory_ID = NULL;

--delete from m_storage WHERE ad_client_id=1000000;;  -- use this for ADempiere
delete from m_storageonhand WHERE ad_client_id=1000000;;
delete from m_storagereservation WHERE ad_client_id=1000000;;
delete from m_transaction WHERE ad_client_id=1000000;;
delete from m_packageline WHERE ad_client_id=1000000;;
delete from m_package WHERE ad_client_id=1000000;;
delete from m_inoutline WHERE ad_client_id=1000000;; 
delete from m_inoutconfirm WHERE ad_client_id=1000000;; 
delete from m_inoutlineconfirm WHERE ad_client_id=1000000;; 
delete from m_inoutlinema WHERE ad_client_id=1000000;; 
update m_product set m_inventory_id = NULL;
delete from m_inventoryline WHERE ad_client_id=1000000;; 
delete from m_inventorylinema  WHERE ad_client_id=1000000;; 
delete from m_Movementline WHERE ad_client_id=1000000;; 
delete from m_Movement WHERE ad_client_id=1000000;; 
delete from m_Movementconfirm WHERE ad_client_id=1000000;; 
delete from m_Movementlineconfirm WHERE ad_client_id=1000000;; 
delete from m_Movementlinema WHERE ad_client_id=1000000;; 
delete from m_production WHERE ad_client_id=1000000;;
delete from m_productionplan WHERE ad_client_id=1000000;; 
delete from m_productionline WHERE ad_client_id=1000000;; 
delete from c_dunningrun WHERE ad_client_id=1000000;; 
delete from c_dunningrunline WHERE ad_client_id=1000000;; 
delete from c_dunningrunentry WHERE ad_client_id=1000000;; 
delete from AD_WF_EventAudit  WHERE ad_client_id=1000000;;
delete from AD_WF_Process WHERE ad_client_id=1000000; ;
Update M_Cost SET CurrentQty=0, CumulatedAMT=0, CumulatedQty=0  WHERE ad_client_id=1000000;
Update C_BPartner SET ActualLifetimeValue=0, SO_CreditUsed=0, TotalOpenBalance=0, C_Invoice_ID=NULL  WHERE ad_client_id=1000000;;
delete from R_RequestUpdates WHERE ad_client_id=1000000;;
delete from R_RequestUpdate WHERE ad_client_id=1000000;;
delete from R_RequestAction WHERE ad_client_id=1000000;;
delete from R_Request WHERE ad_client_id=1000000;;
Delete from pp_cost_collectorma  WHERE ad_client_id=1000000;;
Delete from pp_order_nodenext  WHERE ad_client_id=1000000;;
Delete from pp_order_node_trl  WHERE ad_client_id=1000000;;
Delete from pp_order_workflow_trl  WHERE ad_client_id=1000000;;
Delete from pp_order_bomline_trl  WHERE ad_client_id=1000000;;
Delete from pp_order_bom_trl  WHERE ad_client_id=1000000;;
update pp_cost_collector set pp_order_bomline_id = null WHERE ad_client_id=1000000;;
Delete from pp_order_bomline  WHERE ad_client_id=1000000;;
Delete from pp_order_bom  WHERE ad_client_id=1000000;;
Delete from PP_Cost_Collector  WHERE ad_client_id=1000000;;
Update pp_order_workflow set PP_Order_Node_id = null WHERE ad_client_id=1000000;; 
Delete from PP_Order_Node WHERE ad_client_id=1000000;;
Delete from PP_Order_Workflow  WHERE ad_client_id=1000000;;
Delete from pp_order_cost  WHERE ad_client_id=1000000;;
Delete from PP_Order   WHERE ad_client_id=1000000;;
delete from dd_orderline WHERE ad_client_id=1000000;;
delete from dd_order WHERE ad_client_id=1000000;;
delete from t_replenish WHERE ad_client_id=1000000;;
delete from i_order WHERE ad_client_id=1000000;;
delete from i_invoice WHERE ad_client_id=1000000;;
delete from i_payment WHERE ad_client_id=1000000;;
delete from I_Inventory WHERE ad_client_id=1000000;;
delete from I_GLJournal WHERE ad_client_id=1000000;;
--delete from m_distributionrunline WHERE ad_client_id=1000000;;
delete from c_rfqline WHERE ad_client_id=1000000;;
delete from c_projectline where c_project_id not in (select c_project_id from c_acctschema_element) and ad_client_id=1000000;;
--delete from c_project where c_project_id not in (select c_project_id from c_acctschema_element) and ad_client_id=1000000;;
--update AD_Sequence set currentnext=startno where isTableID='N';

--delete from ChuBoe_Replenish_Action;
--delete from ChuBoe_Replenish_TempAction;
--delete from ChuBoe_Replenish_Date;
--delete from ChuBoe_Replenish_Pressure;
--delete from ChuBoe_Replenish_Storage;
--delete from ChuBoe_Replenish_Run;

DELETE FROM tf_trtaxinvoiceLINE;
DELETE FROM tf_trtaxinvoice;


DELETE FROM M_Cost WHERE ad_client_id=1000000 ;
--DELETE FROM m_bp_price WHERE ad_client_id=1000000 ;

DELETE FROM I_Tally_Ledger WHERE ad_client_id=1000000 ;

delete FROM m_cost WHERE ad_client_id=1000000;

delete FROM fact_acct WHERE ad_client_id=1000000;

delete from c_invoiceline WHERE ad_client_id=1000000;

--DELETE FROM M_Product WHERE ad_client_id=1000000 AND m_product_id>1000032 ;;
--SELECT * FROM M_Product where ad_client_id=1000000 AND m_product_id>1000032 ;;




--DELETE FROM AD_ARCHIVE WHERE ad_client_id=1000000;;
--DELETE FROM HR_EMPLOYEE WHERE ad_client_id=1000000;;

--DELETE FROM AD_USER WHERE AD_CLIENT_ID= 1000000 AND AD_USER_ID NOT IN (1000004, 1000019, 1000005, 1000008);

--DELETE FROM c_bPARTNER WHERE ad_client_id=1000000 AND C_BPARTNER_ID NOT IN (1000010,1000005, 1000007, 1000006, 1000020);


--DELETE FROM adempiere.AD_Image WHERE AD_Client_ID = 1000000;

--DELETE FROM ADEMPIERE.ad_attachment WHERE AD_Client_ID = 1000000;

-- DELETE FROM  M_Product_Category WHERE AD_Client_ID = 1000000 and M_Product_Category_ID=1000029;

-- DELETE FROM  M_Warehouse  WHERE AD_Client_ID = 1000000 and M_Warehouse_ID NOT IN (1000001,1000000);


DELETE FROM TF_Crusher_Production;
DELETE FROM TF_TripSheet;

DELETE FROM AD_RecentItem where ad_client_id=1000000;

DELETE FROM AD_Note where ad_client_id=1000000;
DELETE FROM TF_Labour_Wage;
DELETE FROM TF_Labour_Wage_Advance;

DELETE FROM TF_Employee_Salary_Advance;
DELETE FROM TF_Labour_Wage_Issue;
DELETE FROM TF_Employee_Salary_Issue;

DELETE FROM TF_Vehicle_Rental_Contract;
DELETE FROM TF_Shareholder;
DELETE FROM TF_InvestmentStructure;
DELETE FROM TF_Token;
DELETE FROM  TF_Generate_TaxInvoiceLine WHERE ad_client_id=1000000;
DELETE FROM TF_Generate_TaxInvoice WHERE ad_client_id=1000000;


-- Master ----
--UPDATE c_elementvalue SET C_BankAccount_ID = nuLL WHERE ad_client_id=1000000;
--DELETE FROM tf_orgcashtransfer_config WHERE ad_client_id=1000000;
--DELETE FROM C_BankAccount WHERE ad_client_id=1000000;
--DELETE FROM C_Bank WHERE ad_client_id=1000000;




-- Document Seq Resetting ----
-- CashBook 
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000389;

--Fuel Issue Entry -- DocumentNo_TF_Fuel_Issue
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000377;

--Vehicle Rent Entry -- DocumentNo_TF_Vehicle_Rent
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000402;

-- Employee Salary Entry -- DocumentNo_TF_Employee_Salary
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000391;

-- Boulder Receipt -- DocumentNo_TF_Boulder_Receipt
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000358;


-- Rental Contract  -- DocumentNo_TF_Vehicle_Rental_Contract
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000401;

-- POS Sales Order -- POS Order
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000341;

-- AR Invoice (both AR Invoice & Invoice Indirect) -- AR Invoice
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000307;

-- MM Shipment (both Direct & Indirect) -- MM Shipment
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000312;

-- Purchase Order -- Purchase Order
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000316;

-- Vendor Invoice -- AP Invoice
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000403;

-- Material Receipt -- Material Receipt
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000404;

-- Employee Salary Issue Entry -- DocumentNo_TF_Employee_Salary_Issue
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000397;

-- Labour Wage Issue Entry -- DocumentNo_TF_Labour_Wage_Issue
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000395;

-- Employee Salary Advance Entry -- DocumentNo_TF_Employee_Salary_Advance
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000393;

-- Labour Wage Advance Entry -- DocumentNo_TF_Labour_Wage_Advance
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000390;

-- TripSheet Entry -- DocumentNo_TF_TripSheet
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000381;

-- Labour Wage Entry -- DocumentNo_TF_Labour_Wage
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000387;

-- Crusher Production -- DocumentNo_TF_Crusher_Production
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000374 ;

--  Production -- DocumentNo_M_Production
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=200133 ;

--  DocumentNo_TF_Quarry_Rent -- DocumentNo_TF_Quarry_Rent
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000405 ;

--  GL_Journal -- DocumentNo_TF_Quarry_Rent
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000305 ;

--  DocumentNo_C_Project -- DocumentNo_C_Project
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000239 ;

--  DocumentNo_TF_Jobwork_ItemIssue -- DocumentNo_TF_Jobwork_ItemIssue
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000413 ;

-- DocumentNo TF_Token
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000557;

--  DocumentNo_TF_Jobwork_Expense_Entry -- DocumentNo_TF_Jobwork_Expense_Entry
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000416 ;
DELETE FROM TF_Jobwork_Expense_Entry;

--  DocumentNo_TF_ItemReceipt_OtherSrc -- TF_ItemReceipt_OtherSrc
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000419 ;
DELETE FROM TF_ItemReceipt_OtherSrc;

-- Need to reset Document Seq No for Internal Use Inventory, Physical Inventory and etc...
--  Physical Inventory -
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000323;

--  Internal Use Inventory -
UPDATE AD_Sequence SET CurrentNext = 1 WHERE AD_Sequence_ID=1000326;


DELETE FROM AD_Sequence_No WHERE ad_client_id=1000000;
DELETE FROM TF_DaySeq;

DELETE FROM TF_Boulder_Movement;

DELETE FROM tf_vehiclerent_tajconfig;


DELETE FROM AD_Preference WHERE AD_uSER_id in (1000705);
DELETE FROM ad_password_history WHERE AD_uSER_id in (1000705);
DELETE FROM ad_userpreference WHERE AD_uSER_id in (1000705);pa_dashboardpreference
DELETE FROM pa_dashboardpreference WHERE AD_uSER_id in (1000705);*/
DELETE FROM c_allocationline;
DELETE FROM c_allocationhdr;
DELETE FROM c_bankstatementline;
DELETE FROM c_bankstatement;
UPDATE c_invoice SET c_payment_id = NULL;
UPDATE c_order SET c_payment_id = NULL;


DELETE FROM tf_weighmententry;
DELETE FROM c_payment WHERE ad_client_id=1000000;
DELETE FROM c_Invoice WHERE ad_client_id=1000000;
DELETE FROM m_inout WHERE ad_client_id=1000000;
DELETE FROM c_order WHERE ad_client_id=1000000;
DELETE FROM m_inventory WHERE ad_client_id=1000000;
