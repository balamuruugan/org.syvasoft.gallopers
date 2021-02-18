/***/DROP VIEW IF EXISTS tf_allocation_v;

/***/CREATE OR REPLACE VIEW tf_allocation_v AS
 /***/SELECT a.ad_client_id,
    a.ad_org_id,
    a.docstatus,
    a.dateacct,
    i.paymentrule,
    ((
        CASE
            WHEN (al.discountamt < (0)::numeric) THEN 'By Payment Discount Revenue from Allocation Document'::text
            ELSE 'To Payment Discount Expense from Allocation Document'::text
        END ||
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN ' | Discount given for Sales Invoice : '::text
            ELSE ' | Discount received for Purchase Invoice : '::text
        END) || (i.documentno)::text) AS description,
    a.documentno,
    NULL::text AS vehicle,
    NULL::text AS destination,
    NULL::text AS item,
    NULL::text AS qty,
    NULL::text AS price,
        CASE
            WHEN (al.discountamt < (0)::numeric) THEN abs(al.discountamt)
            ELSE NULL::numeric
        END AS discountamt_debit,
        CASE
            WHEN (al.discountamt > (0)::numeric) THEN al.discountamt
            ELSE NULL::numeric
        END AS discountamt_credit,
    0 AS writeoffamt_debit,
    0 AS writeoffamt_credit,
    i.c_bpartner_id,
    bp.name AS bpname,
    0 AS seq,
    cbp.c_bpartner_id AS parentc_bpartner_id,
    cbp.name AS parentbpname
   
FROM (((((c_allocationhdr a
     JOIN c_allocationline al ON ((a.c_allocationhdr_id = al.c_allocationhdr_id)))
     LEFT JOIN c_invoice i ON ((i.c_invoice_id = al.c_invoice_id)))
     LEFT JOIN c_bpartner bp ON ((bp.c_bpartner_id = i.c_bpartner_id)))
     LEFT JOIN tf_bpartner_link cbpl ON ((cbpl.linked_bpartner_id = bp.c_bpartner_id)))
     LEFT JOIN c_bpartner cbp ON ((cbpl.c_bpartner_id = cbp.c_bpartner_id)))
  WHERE ((a.ad_client_id = (1000000)::numeric) AND (a.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (al.discountamt <> (0)::numeric))
UNION
 /***/SELECT a.ad_client_id,
    a.ad_org_id,
    a.docstatus,
    a.dateacct,
    i.paymentrule,
    ((
        CASE
            WHEN (al.writeoffamt < (0)::numeric) THEN 'By Bad Debts Write-off from Allocation Document'::text
            ELSE 'To Bad Debts Write-off from Allocation Document'::text
        END ||
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN ' for Sales Invoice : '::text
            ELSE '  for Purchase Invoice : '::text
        END) || (i.documentno)::text) AS description,
    a.documentno,
    NULL::text AS vehicle,
    NULL::text AS destination,
    NULL::text AS item,
    NULL::text AS qty,
    NULL::text AS price,
    0 AS discountamt_debit,
    0 AS discountamt_credit,
        CASE
            WHEN (al.writeoffamt < (0)::numeric) THEN abs(al.writeoffamt)
            ELSE NULL::numeric
        END AS writeoffamt_debit,
        CASE
            WHEN (al.writeoffamt > (0)::numeric) THEN al.writeoffamt
            ELSE NULL::numeric
        END AS writeoffamt_credit,
    i.c_bpartner_id,
    bp.name AS bpname,
    0 AS seq,
    cbp.c_bpartner_id AS parentc_bpartner_id,
    cbp.name AS parentbpname
   
FROM (((((c_allocationhdr a
     JOIN c_allocationline al ON ((a.c_allocationhdr_id = al.c_allocationhdr_id)))
     LEFT JOIN c_invoice i ON ((i.c_invoice_id = al.c_invoice_id)))
     LEFT JOIN c_bpartner bp ON ((bp.c_bpartner_id = i.c_bpartner_id)))
     LEFT JOIN tf_bpartner_link cbpl ON ((cbpl.linked_bpartner_id = bp.c_bpartner_id)))
     LEFT JOIN c_bpartner cbp ON ((cbpl.c_bpartner_id = cbp.c_bpartner_id)))
  WHERE ((a.ad_client_id = (1000000)::numeric) AND (a.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (al.writeoffamt <> (0)::numeric));

