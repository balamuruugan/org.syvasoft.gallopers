/***/DROP VIEW IF EXISTS tf_payment_v;

/***/CREATE OR REPLACE VIEW tf_payment_v AS
 /***/SELECT p.ad_client_id,
    p.ad_org_id,
    p.docstatus,
    p.dateacct,
    p.c_invoice_id,
        CASE
            WHEN (p.c_invoice_id IS NOT NULL) THEN i.paymentrule
            ELSE bp.paymentrule
        END AS paymentrule,
    (((
        CASE
            WHEN ((p.c_invoice_id IS NOT NULL) AND (p.isreceipt = 'Y'::bpchar)) THEN (('To Cash Receipt for Sales Invoice : '::text || (i.documentno)::text) || (COALESCE(p.description, ''::character varying))::text)
            WHEN ((p.c_invoice_id IS NOT NULL) AND (p.isreceipt = 'N'::bpchar)) THEN (('By Cash Payment for Purchase Invoice : '::text || (i.documentno)::text) || (COALESCE(p.description, ''::character varying))::text)
            WHEN (p.isreceipt = 'Y'::bpchar) THEN ('To Cash Receipt - '::text || (COALESCE(p.description, ''::character varying))::text)
            WHEN (p.isreceipt = 'N'::bpchar) THEN ('By Cash Payment - '::text || (COALESCE(p.description, ''::character varying))::text)
            ELSE ''::text
        END || COALESCE((' | '::text || (p.description)::text), ''::text)) || COALESCE(((' ('::text || (pr.name)::text) || ')'::text), ''::text)) || COALESCE(((' [For  '::text || (e.name)::text) || ']'::text), ''::text)) AS description,
    p.documentno,
    p.documentno2,
    p.isreceipt,
    NULL::unknown AS vehicleno,
    NULL::unknown AS destination,
    NULL::unknown AS item,
    NULL::unknown AS qty,
    NULL::unknown AS price,
        CASE
            WHEN (p.isreceipt = 'N'::bpchar) THEN p.payamt
            ELSE NULL::numeric
        END AS debit,
        CASE
            WHEN (p.isreceipt = 'Y'::bpchar) THEN p.payamt
            ELSE NULL::numeric
        END AS credit,
    p.c_bpartner_id,
    bp.name AS bpname,
    bp.isemployee,
    cbp.c_bpartner_id AS parentc_bpartner_id,
    cbp.name AS parentbpname
   
FROM (((((((c_payment p
     JOIN c_bpartner bp ON ((bp.c_bpartner_id = p.c_bpartner_id)))
     LEFT JOIN tf_bpartner_link cbpl ON ((cbpl.linked_bpartner_id = bp.c_bpartner_id)))
     LEFT JOIN c_bpartner cbp ON ((cbpl.c_bpartner_id = cbp.c_bpartner_id)))
     LEFT JOIN c_charge c ON ((c.c_charge_id = p.c_charge_id)))
     LEFT JOIN c_invoice i ON ((i.c_invoice_id = p.c_invoice_id)))
     LEFT JOIN c_project pr ON ((pr.c_project_id = p.c_project_id)))
     LEFT JOIN c_elementvalue e ON ((e.c_elementvalue_id = p.user1_id)))
  WHERE ((p.ad_client_id = (1000000)::numeric) AND (bp.isemployee = 'N'::bpchar) AND (p.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (p.c_bpartner_id IS NOT NULL));

