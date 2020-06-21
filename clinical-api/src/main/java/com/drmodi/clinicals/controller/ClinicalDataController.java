package com.drmodi.clinicals.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drmodi.clinicals.dto.ClinicalDataRequest;
import com.drmodi.clinicals.model.ClinicalData;
import com.drmodi.clinicals.model.Patient;
import com.drmodi.clinicals.repositories.ClinicalDataRepository;
import com.drmodi.clinicals.repositories.PatientRepository;
import com.drmodi.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {
	
	private ClinicalDataRepository clinicalRepository;
	private PatientRepository patientRepository;
	
	@Autowired
	public ClinicalDataController(ClinicalDataRepository clinicalRepository, 
				PatientRepository patientRepository) {
		this.clinicalRepository = clinicalRepository;
		this.patientRepository = patientRepository;
	}
	
	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		Patient patient = patientRepository.findById(request.getPatientId()).get();
		ClinicalData data = new ClinicalData();
		data.setComponentName(request.getComponentName());
		data.setComponentValue(request.getComponentValue());
		data.setPatient(patient);
		
		return clinicalRepository.save(data);
	}
	
	@RequestMapping(value = "clinicals/{patientId}/{componentName}", method = RequestMethod.POST)
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId, 
			@PathVariable("componentName") String componentName){
		
		if (componentName.equalsIgnoreCase("bmi")) {
			componentName="hw";
		}
		
		List<ClinicalData> clinicalDataList = clinicalRepository
				.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
		ArrayList<ClinicalData> duplicateList = new ArrayList<>(clinicalDataList);
		
		for(ClinicalData eachEntry:duplicateList) {
			BMICalculator.calculateBMI(clinicalDataList, eachEntry);
		}
		return clinicalDataList;
		
	}

}

