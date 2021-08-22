-- View: adempiere.m_storeageonhand_v

DROP VIEW adempiere.m_storeageonhand_v;

CREATE  VIEW adempiere.m_storeageonhand_v
 AS
 SELECT s.ad_client_id,
    s.ad_org_id,
    max(s.created) AS created,
    max(s.createdby) AS createdby,
    max(s.updated) AS updated,
    max(s.updatedby) AS updatedby,
    w.m_warehouse_id,
    s.m_locator_id,
    l.value AS locator,
	s.m_product_id m_storeageonhand_v,
    s.m_product_id,
    sum(s.qtyonhand) AS qtyonhand,
    s.isactive,
    max(p.c_uom_id) AS c_uom_id
   FROM m_storageonhand s
     JOIN m_locator l ON s.m_locator_id = l.m_locator_id
     LEFT JOIN m_product p ON p.m_locator_id = l.m_locator_id
     LEFT JOIN c_uom u ON u.c_uom_id = p.c_uom_id
     JOIN m_warehouse w ON l.m_warehouse_id = w.m_warehouse_id
  GROUP BY s.ad_client_id, s.ad_org_id, w.m_warehouse_id, s.m_locator_id, l.value, s.m_product_id, s.isactive;
