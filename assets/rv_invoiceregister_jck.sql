/***/DROP VIEW adempiere.rv_invoiceregister;

/***/CREATE  VIEW adempiere.rv_invoiceregister
 AS
 /***/SELECT i.c_invoice_id,
    i.ad_client_id,
    i.ad_org_id,
    i.isactive,
    i.created,
    i.createdby,
    i.updated,
    i.updatedby,
    i.issotrx,
    i.documentno,
    i.docstatus,
    i.docaction,
    i.processing,
    i.processed,
    i.posted,
    i.c_doctype_id,
    i.c_doctypetarget_id,
    i.c_order_id,
    i.description,
    i.isapproved,
    i.istransferred,
    i.isprinted,
    i.salesrep_id,
    i.dateinvoiced,
    i.dateprinted,
    TRUNC(i.dateacct) dateacct,
    i.c_bpartner_id,
    i.c_bpartner_location_id,
    i.poreference,
    i.isdiscountprinted,
    i.dateordered,
    i.c_currency_id,
    i.paymentrule,
    i.c_paymentterm_id,
    i.c_charge_id,
    i.chargeamt,
    i.totallines,
    i.grandtotal,
    i.m_pricelist_id,
    i.istaxincluded,
    i.c_campaign_id,
    i.c_project_id,
    i.c_activity_id,
    i.ispaid,
    i.c_payment_id,
    i.c_cashline_id,
    i.createfrom,
    i.generateto,
    i.sendemail,
    i.ad_user_id,
    i.copyfrom,
    i.isselfservice,
    i.ad_orgtrx_id,
    i.user1_id,
    i.user2_id,
    i.c_conversiontype_id,
    i.ispayschedulevalid,
    i.ref_invoice_id,
    i.isindispute,
    i.invoicecollectiontype,
    i.m_rma_id,
    i.dunninggrace,
    i.c_dunninglevel_id,
    i.reversal_id,
    i.processedon,
    i.c_cashplanline_id,
    i.c_invoice_uu,
    i.isfixedassetinvoice,
    i.syncstatus,
    i.item1_id,
    i.item1_qty,
    i.item1_price,
    i.item1_amt,
    i.item1_c_invoiceline_id,
    i.item2_amt,
    i.item2_c_invoiceline_id,
    i.item2_price,
    i.item2_qty,
    i.item2_id,
    i.m_warehouse_id,
    i.vehicleno,
    i.tf_vehicle_rental_contract_id,
    i.gl_journal_id,
    i.datefrom,
    i.dateto,
    i.createquarrysubcontractinvline,
    i.tf_weighmententry_id,
    i.subcontracttype,
    i.createcrushersubcontractinvline,
    i.createinvlinefrommatreceipt,
    w.documentno AS ticketno,
    o.drivertips,
    o.salesdiscountamt,
    w.invoiceno AS gstinvno,
    w.tf_destination_id,
    o.tf_rentedvehicle_id,
    COALESCE(i.poreference, o.poreference) AS supplierreferenceno,
        CASE
            WHEN w.partyname::text ~~ (('%'::text || bp.name::text) || '%'::text) THEN NULL::text
            ELSE w.partyname::text
        END AS partyname,
    il.c_invoiceline_id,
    il.line,
    il.m_product_id,
    il.c_uom_id,
    il.description AS linedescription,
    il.qtyentered,
    il.priceentered,
    il.c_tax_id,
    il.linenetamt,
    ( /***/SELECT t_1.rate
           FROM c_tax t_1
          WHERE t_1.c_tax_id = il.c_tax_id) AS taxrate,
        CASE
            WHEN il.linetotalamt > 0::numeric THEN il.linetotalamt - il.linenetamt
            ELSE 0::numeric
        END AS gstamount,
	CASE WHEN tx.IsInterState = 'N' THEN Round((il.linenetamt * tx.Rate/100)/ 2 ,2) ELSE NULL  END SGST,	
	CASE WHEN tx.IsInterState = 'N' THEN Round((il.linenetamt * tx.Rate/100)/ 2 ,2) ELSE NULL  END CGST,
	CASE WHEN tx.IsInterState = 'Y' THEN Round(il.linenetamt * tx.Rate/100,2) ELSE NULL END IGST,
    il.linetotalamt,
    t.c_bpartner_id AS transporter_id,
    bp.c_bp_group_id,
	bp.TaxID,
	p.HSNCode,
	p.m_product_category_id
   FROM c_invoice i
     LEFT JOIN c_order o ON o.c_order_id = i.c_order_id
     JOIN c_invoiceline il ON i.c_invoice_id = il.c_invoice_id
	 JOIN C_tax tx ON il.C_Tax_ID = tx.C_Tax_ID
     JOIN m_product p ON il.m_product_id = p.m_product_id
     JOIN m_product_category pc ON pc.m_product_category_id = p.m_product_category_id
     LEFT JOIN tf_weighmententry w ON i.tf_weighmententry_id = w.tf_weighmententry_id
     JOIN c_bpartner bp ON bp.c_bpartner_id = i.c_bpartner_id
     JOIN c_doctype dc ON dc.c_doctype_id = i.c_doctypetarget_id
     LEFT JOIN tf_rentedvehicle rv ON rv.tf_rentedvehicle_id = o.tf_rentedvehicle_id
     LEFT JOIN tf_destination d ON d.tf_destination_id = w.tf_destination_id
     LEFT JOIN tf_vehicletype vt ON vt.tf_vehicletype_id = rv.tf_vehicletype_id
     LEFT JOIN m_warehouse wh ON wh.m_warehouse_id = o.m_warehouse_id
     LEFT JOIN c_bpartner t ON t.c_bpartner_id = rv.c_bpartner_id
     LEFT JOIN ad_ref_list rl ON rl.value::bpchar = o.paymentrule AND rl.ad_reference_id = 195::numeric
	 
  WHERE (i.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (dc.docbasetype = ANY (ARRAY['ARI'::bpchar, 'API'::bpchar]))
  ORDER BY i.ad_client_id, i.ad_org_id, i.dateacct, i.documentno;

