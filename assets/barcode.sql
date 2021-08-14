CREATE VIEW TF_Numbers AS
SELECT
		ROW_NUMBER () OVER (ORDER BY M_Product_id) rownumber
FROM
	M_Product;
	

SELECT
	Barcode, LEFT(Name,38) Name
FROM
	M_Product p CROSS JOIN TF_Numbers n
WHERE
	M_Product_ID IN (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID=$P{AD_PINSTANCE_ID}) 
	AND rownumber <= $P{Qty}
ORDER BY Name
	
	
	
