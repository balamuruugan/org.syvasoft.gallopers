/***/DROP VIEW IF EXISTS tf_acct_invoice_v;

/***/CREATE OR REPLACE VIEW tf_acct_invoice_v AS
 /***/SELECT i.ad_client_id,
    i.ad_org_id,
    i.docstatus,
    COALESCE(we.grossweighttime, i.dateacct) AS dateacct,
    i.issotrx,
    i.paymentrule,
    ((
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN 'By Sales'::text
            ELSE 'To Purchase'::text
        END ||
        CASE
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN (((((((( /***/SELECT ((((((' | Vehicle Rent, distance: '::text || ot.distance) || 'Km '::text) ||
                    CASE
                        WHEN ((ot.islumpsumrent = 'Y'::bpchar) OR (1 = 1)) THEN (' for Rs'::text || to_char(il.priceentered, '99,999.99'::text))
                        ELSE ('@ Rs'::text || to_char((il.priceentered / ot.tonnage), '999.99'::text))
                    END) || ' for '::text) || ot.tonnage) || 'MT'::text)
               
FROM tf_destination d2
              WHERE (d2.tf_destination_id = ot.tf_destination_id)) || ' | '::text) || ' Delivered '::text) ||
            CASE
                WHEN (ot.issotrx = 'Y'::bpchar) THEN 'to '::text
                ELSE 'from '::text
            END) || (( /***/SELECT b1.name
               
FROM c_bpartner b1
              WHERE (b1.c_bpartner_id = ot.c_bpartner_id)))::text) || ' ('::text) || (ot.documentno)::text) || ')'::text)
            ELSE (COALESCE((' | '::text || (i.description)::text), ''::text) || COALESCE((' | '::text || (il.description)::text), ''::text))
        END) || COALESCE((' | Ticket No: '::text || (we.documentno)::text), ''::text)) AS description,
    i.documentno,
    w.documentno AS dcno,
    ot.documentno AS transpno,
        CASE
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN p.name
            ELSE COALESCE(i.vehicleno, COALESCE(v.vehicleno, o.vehicleno))
        END AS vehicleno,
        CASE
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN ( /***/SELECT d1.name
               
FROM tf_destination d1
              WHERE (d1.tf_destination_id = ot.tf_destination_id))
            ELSE d.name
        END AS destination,
        CASE
            WHEN ((i.issotrx = 'Y'::bpchar) AND ((v.vehicleno)::text = (p.name)::text)) THEN ''::character varying
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN ''::character varying
            WHEN (p.c_uom_id = (106)::numeric) THEN ((((((p.name)::text || ' ('::text) || (u.name)::text) || ')'::text) || COALESCE((' - '::text || (o.description)::text), ''::text)))::character varying
            WHEN (p.name IS NOT NULL) THEN (((((p.name)::text || ' ('::text) || (u.name)::text) || ')'::text))::character varying
            WHEN ((c.name)::text ~~ '%Opening%'::text) THEN ''::character varying
            ELSE c.name
        END AS item,
        CASE
            WHEN ((i.issotrx = 'Y'::bpchar) AND ((v.vehicleno)::text = (p.name)::text)) THEN NULL::numeric
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN il.qtyentered
            WHEN ((c.name)::text ~~ '%Opening%'::text) THEN NULL::numeric
            ELSE il.qtyentered
        END AS qtyinvoiced,
        CASE
            WHEN ((i.issotrx = 'Y'::bpchar) AND ((v.vehicleno)::text = (p.name)::text)) THEN NULL::numeric
            WHEN ((COALESCE(ot.documentno, 'N'::character varying))::text <> 'N'::text) THEN il.priceentered
            WHEN ((c.name)::text ~~ '%Opening%'::text) THEN NULL::numeric
            ELSE il.priceentered
        END AS priceentered,
        CASE
            WHEN (i.issotrx = 'Y'::bpchar) THEN il.linenetamt
            ELSE NULL::numeric
        END AS debit,
        CASE
            WHEN (i.issotrx = 'N'::bpchar) THEN il.linenetamt
            ELSE NULL::numeric
        END AS credit,
    p.m_product_id,
    pt.name AS tproductname,
    bpt.c_bpartner_id AS tbpartner_id,
    bpt.name AS tbpname,
    i.c_bpartner_id,
    bp.name AS bpname,
    0 AS c_tax_id,
    'GST'::character varying AS taxname,
    5 AS taxrate,
    0 AS taxbaseamt,
    itx.taxamt,
    itx.istaxincluded,
    u.uomtype,
    u.name AS uomname,
    o.drivertips,
    cbp.c_bpartner_id AS parentc_bpartner_id,
    cbp.name AS parentbpname
   
FROM (((((((((((((((((c_invoice i
     JOIN c_invoiceline il ON ((i.c_invoice_id = il.c_invoice_id)))
     JOIN c_doctype dt ON ((i.c_doctypetarget_id = dt.c_doctype_id)))
     LEFT JOIN c_bpartner bp ON ((bp.c_bpartner_id = i.c_bpartner_id)))
     LEFT JOIN tf_bpartner_link cbpl ON ((cbpl.linked_bpartner_id = bp.c_bpartner_id)))
     LEFT JOIN c_bpartner cbp ON ((cbpl.c_bpartner_id = cbp.c_bpartner_id)))
     LEFT JOIN m_product p ON ((il.m_product_id = p.m_product_id)))
     LEFT JOIN c_order o ON ((i.c_order_id = o.c_order_id)))
     LEFT JOIN tf_weighmententry we ON ((o.tf_weighmententry_id = we.tf_weighmententry_id)))
     LEFT JOIN c_uom u ON ((il.c_uom_id = u.c_uom_id)))
     LEFT JOIN c_charge c ON ((il.c_charge_id = c.c_charge_id)))
     LEFT JOIN tf_rentedvehicle v ON ((o.tf_rentedvehicle_id = v.tf_rentedvehicle_id)))
     LEFT JOIN tf_destination d ON ((o.tf_destination_id = d.tf_destination_id)))
     LEFT JOIN tf_weighmententry w ON ((w.tf_weighmententry_id = i.tf_weighmententry_id)))
     LEFT JOIN c_order ot ON ((ot.transporterinvoice_id = i.c_invoice_id)))
     LEFT JOIN c_bpartner bpt ON ((bpt.c_bpartner_id = ot.c_bpartner_id)))
     LEFT JOIN m_product pt ON ((pt.m_product_id = ot.item1_id)))
     LEFT JOIN ( /***/SELECT it.c_invoice_id,
            sum(it.taxamt) AS taxamt,
            it.istaxincluded
           
FROM c_invoicetax it
          GROUP BY it.c_invoice_id, it.istaxincluded) itx ON ((i.c_invoice_id = itx.c_invoice_id)))
  WHERE ((i.ad_client_id = (1000000)::numeric) AND (i.processed = 'Y'::bpchar) AND (i.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND (dt.docbasetype = ANY (ARRAY['ARI'::bpchar, 'API'::bpchar])));

