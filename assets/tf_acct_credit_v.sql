/***/DROP VIEW IF EXISTS tf_acct_credit_v;

/***/CREATE OR REPLACE VIEW tf_acct_credit_v AS
 /***/SELECT i.ad_client_id,
    i.ad_org_id,
    i.docstatus,
    i.dateacct,
    i.issotrx,
    i.paymentrule,
    (((
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN 'To Credit Note'::text
            ELSE 'By Debit Note'::text
        END || COALESCE((' | '::text || (i.description)::text), ''::text)) || COALESCE((' | '::text || (il.description)::text), ''::text)) || COALESCE(((' | '::text || (c.name)::text) || ' incurred'::text), ''::text)) AS description,
    i.documentno,
    w.documentno AS dcno,
    COALESCE(v.vehicleno, o.vehicleno) AS vehicleno,
    d.name AS destination,
        CASE
            WHEN (p.name IS NOT NULL) THEN ((((p.name)::text || ' ('::text) || (u.name)::text) || ')'::text)
            WHEN (e.name IS NOT NULL) THEN (e.name)::text
            ELSE ''::text
        END AS item,
        CASE
            WHEN (c.name IS NULL) THEN il.qtyentered
            ELSE NULL::numeric
        END AS qtyinvoiced,
        CASE
            WHEN (c.name IS NULL) THEN il.priceentered
            ELSE NULL::numeric
        END AS priceentered,
        CASE
            WHEN (i.issotrx = 'N'::bpchar) THEN il.linenetamt
            ELSE NULL::numeric
        END AS debit,
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN il.linenetamt
            ELSE NULL::numeric
        END AS credit,
    p.m_product_id,
    i.c_bpartner_id,
    bp.name AS bpname,
    t.c_tax_id,
    t.name AS taxname,
    t.rate AS taxrate,
    it.taxbaseamt,
    it.taxamt,
    it.istaxincluded,
    u.uomtype,
    u.name AS uomname,
    cbp.c_bpartner_id AS parentc_bpartner_id,
    cbp.name AS parentbpname
   
FROM (((((((((((((((c_invoice i
     JOIN c_invoiceline il ON ((i.c_invoice_id = il.c_invoice_id)))
     JOIN c_doctype dt ON ((i.c_doctypetarget_id = dt.c_doctype_id)))
     LEFT JOIN c_bpartner bp ON ((bp.c_bpartner_id = i.c_bpartner_id)))
     LEFT JOIN tf_bpartner_link cbpl ON ((cbpl.linked_bpartner_id = bp.c_bpartner_id)))
     LEFT JOIN c_bpartner cbp ON ((cbpl.c_bpartner_id = cbp.c_bpartner_id)))
     LEFT JOIN m_product p ON ((il.m_product_id = p.m_product_id)))
     LEFT JOIN c_order o ON ((i.c_order_id = o.c_order_id)))
     LEFT JOIN c_uom u ON ((il.c_uom_id = u.c_uom_id)))
     LEFT JOIN c_charge c ON ((il.c_charge_id = c.c_charge_id)))
     LEFT JOIN c_elementvalue e ON ((e.c_elementvalue_id = c.c_elementvalue_id)))
     LEFT JOIN tf_rentedvehicle v ON ((o.tf_rentedvehicle_id = v.tf_rentedvehicle_id)))
     LEFT JOIN tf_destination d ON ((o.tf_destination_id = d.tf_destination_id)))
     LEFT JOIN tf_weighmententry w ON ((w.tf_weighmententry_id = i.tf_weighmententry_id)))
     JOIN c_invoicetax it ON ((i.c_invoice_id = it.c_invoice_id)))
     JOIN c_tax t ON ((t.c_tax_id = it.c_tax_id)))
  WHERE ((i.ad_client_id = (1000000)::numeric) AND (i.processed = 'Y'::bpchar) AND (i.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (dt.docbasetype = ANY (ARRAY['ARC'::bpchar, 'APC'::bpchar])));

