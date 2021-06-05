SELECT
	a.*,
	a.ClosingMeter - a.OpeningMeter RunningMeter,
	CASE a.MileageType 
		WHEN 'H' THEN (a.DieselIssued / (a.ClosingMeter - a.OpeningMeter))
		WHEN 'K' THEN ((a.ClosingMeter - a.OpeningMeter) / a.DieselIssued)
		ELSE null			
	END Mileage
FROM
	
	(SELECT
		MAX(m.MachineryNo) MachineryNo,
		f.MileageType,
		(SELECT SUM(d.QTY) FROM TF_Fuel_Issue d WHERE 
		 d.TF_Fuel_Issue_ID > MIN(f.TF_Fuel_Issue_ID) AND
		 d.TF_Fuel_Issue_ID <= MAX(f.TF_Fuel_Issue_ID) AND
		 d.DocStatus = 'CO' AND d.PM_Machinery_ID = f.PM_Machinery_ID AND
		 d.M_Product_ID = f.M_Product_ID)	 
		DieselIssued,
		(SELECT minf.IssueMeter FROM TF_Fuel_Issue minf WHERE 
		 minf.TF_Fuel_Issue_ID =  MIN(f.TF_Fuel_Issue_ID)) OpeningMeter,	

		(SELECT maxf.IssueMeter FROM TF_Fuel_Issue maxf WHERE 
		 maxf.TF_Fuel_Issue_ID =  MAX(f.TF_Fuel_Issue_ID)) ClosingMeter	

	FROM
		TF_Fuel_Issue f INNER JOIN PM_Machinery m
		 ON f.PM_Machinery_ID = m.PM_Machinery_ID
		INNER JOIN PM_MachineryType mt
		 ON m.PM_MachineryType_ID = mt.PM_MachineryType_ID
	WHERE
		f.AD_Org_ID = 1000003 
		AND f.M_Product_ID IN (1001019, 1000086)
		AND f.DocStatus = 'CO' AND f.IsFullTank = 'Y'
		AND f.DateAcct >= '2021-05-25' 
	
	GROUP BY
		f.PM_Machinery_ID, f.M_Product_ID, f.MileageType) AS a