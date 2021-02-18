-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8212, number of rows retrieved: 20802308

DROP VIEW IF EXISTS tf_weighmententry_v;

CREATE OR REPLACE VIEW tf_weighmententry_v AS
 SELECT w.tf_weighmententry_id,
    w.ad_org_id,
    w.ad_client_id,
    w.isactive,
    w.documentno,
    w.weighmententrytype,
    trl.name AS typename,
    w.c_bpartner_id,
    b.name AS customer,
    w.tf_quarry_id,
    q.name AS quarryname,
    w.c_project_id,
    pc.name AS subcontract,
    w.tf_rentedvehicle_id,
    r.vehicleno AS rentedvehicleno,
    w.vehicleno,
    w.m_product_id,
    p.name AS productname,
    w.grossweight,
    w.grossweighttime,
    w.tareweight,
    w.tareweighttime,
    w.netweight,
    w.description,
    w.status,
    srl.name AS statusdesc,
    w.username,
    w.tf_destination_id,
    d.name AS destination,
    w.paymentrule,
    prl.name AS paymentruledesc,
    w.m_warehouse_id,
    wh.name AS warehouse,
    w.ismanual,
    w.partyname,
    w.phone,
    w.price,
    w.amount,
    w.tf_vehicletype_id,
    vt.name AS vehicletypename,
    w.tf_send_to,
    strl.name AS sendtodesc,
    w.tf_productionplant_id,
        CASE
            WHEN (w.tf_send_to = 'S'::bpchar) THEN COALESCE(pp.m_warehouse_id, (1000001)::numeric)
            ELSE pp.m_warehouse_id
        END AS tf_ppwarehouse_id,
    pp.name AS productionplant,
    ( SELECT
                CASE
                    WHEN (w.tf_send_to = 'S'::bpchar) THEN cpp.name
                    ELSE dwh.name
                END AS name
           
FROM m_warehouse cpp
          WHERE (cpp.m_warehouse_id = (1000001)::numeric)) AS ppwarehouse,
    w.tf_bluemetal_type AS tf_produtiontype_id,
    ptrl.name AS produtiontype,
    COALESCE(w.netweightunit, round((w.netweight / (1000)::numeric), 2)) AS netweightunit,
    w.c_uom_id,
    c.name AS uom,
    w.drivertips,
    w.rent_amt,
    w.totalamt,
    w.invoiceno,
    w.permitpassamount,
    w.passqtyissued,
    w.passpriceperunit,
    w.tenderamount,
    w.changeamt,
    w.grossweighttime AS created,
    w.m_product2_id,
    p2.name AS product2,
    w.drivername,
    w.billingname,
    w.ispermitsales,
    w.discountamount,
    w.roundingoff,
    w.completedby,
    w.gstamount,
    w.newproduct,
    tb.name AS transporter
   
FROM (((((((((((((((((((((((tf_weighmententry w
     JOIN tf_vehicletype vt ON ((w.tf_vehicletype_id = vt.tf_vehicletype_id)))
     JOIN m_product p ON ((w.m_product_id = p.m_product_id)))
     LEFT JOIN c_bpartner b ON ((b.c_bpartner_id = w.c_bpartner_id)))
     JOIN ad_ref_list trl ON (((trl.value)::text = (w.weighmententrytype)::text)))
     JOIN ad_reference tr ON ((trl.ad_reference_id = tr.ad_reference_id)))
     JOIN ad_ref_list prl ON (((prl.value)::bpchar = w.paymentrule)))
     JOIN ad_reference pr ON ((prl.ad_reference_id = pr.ad_reference_id)))
     JOIN ad_ref_list srl ON (((srl.value)::text = (w.status)::text)))
     JOIN ad_reference sr ON ((srl.ad_reference_id = sr.ad_reference_id)))
     LEFT JOIN ad_ref_list strl ON (((strl.value)::bpchar = w.tf_send_to)))
     JOIN ad_reference str ON ((strl.ad_reference_id = str.ad_reference_id)))
     LEFT JOIN ad_ref_list ptrl ON (((ptrl.value)::text = (w.tf_bluemetal_type)::text)))
     LEFT JOIN ad_reference ptr ON (((ptrl.ad_reference_id = ptr.ad_reference_id) AND ((ptr.name)::text = 'TF Production Type'::text))))
     LEFT JOIN tf_destination d ON ((d.tf_destination_id = w.tf_destination_id)))
     LEFT JOIN tf_quarry q ON ((q.tf_quarry_id = w.tf_quarry_id)))
     LEFT JOIN c_uom c ON ((c.c_uom_id = w.c_uom_id)))
     LEFT JOIN c_project pc ON ((pc.c_project_id = w.c_project_id)))
     LEFT JOIN tf_rentedvehicle r ON ((r.tf_rentedvehicle_id = w.tf_rentedvehicle_id)))
     LEFT JOIN m_warehouse wh ON ((w.m_warehouse_id = wh.m_warehouse_id)))
     LEFT JOIN tf_productionplant pp ON ((pp.tf_productionplant_id = w.tf_productionplant_id)))
     LEFT JOIN m_warehouse dwh ON ((dwh.m_warehouse_id = pp.m_warehouse_id)))
     LEFT JOIN m_product p2 ON ((p2.m_product_id = w.m_product2_id)))
     LEFT JOIN c_bpartner tb ON ((tb.c_bpartner_id = r.c_bpartner_id)))
  WHERE (((tr.name)::text = 'WeighmentEntryType'::text) AND ((pr.name)::text = '_Payment Rule'::text) AND ((sr.name)::text = 'WeighmentEntry Status'::text) AND ((str.name)::text = 'TF Send To'::text));

