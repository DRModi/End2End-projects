package com.drmodi.clinicals.util;

import java.util.List;

import com.drmodi.clinicals.model.ClinicalData;

public class BMICalculator {

	public static void calculateBMI(List<ClinicalData> clinicalDataList, ClinicalData eachEntry) {
		if(eachEntry.getComponentName().equalsIgnoreCase("hw")){
			String[] heightAndWeight = eachEntry.getComponentValue().split("/");
			if(heightAndWeight!=null && heightAndWeight.length>1) {
				float heightInMeters = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
				float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);
				
				ClinicalData data = new ClinicalData();
				data.setComponentName("bmi");
				data.setComponentValue(Float.toString(bmi));
				clinicalDataList.add(data);
			}
		}
	}
}
