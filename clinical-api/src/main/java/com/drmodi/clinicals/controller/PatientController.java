package com.drmodi.clinicals.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drmodi.clinicals.model.ClinicalData;
import com.drmodi.clinicals.model.Patient;
import com.drmodi.clinicals.repositories.PatientRepository;
import com.drmodi.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {
	
	PatientRepository repository;
	
	HashMap<String, String> filter = new HashMap<>();
	
	@Autowired
	public PatientController(PatientRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients(){
		return repository.findAll();
		
	}
	
	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.POST )
	public Patient savePatient(@RequestBody Patient patient) {
		return repository.save(patient);
	}
	
	@RequestMapping(value = "patients/analyze/{id}", method = RequestMethod.GET)
	public Patient analyzeData(@PathVariable("id") int id) {
		Patient patient = repository.findById(id).get();
		List<ClinicalData> clinicalDataList = patient.getClinicalData();
		ArrayList<ClinicalData> duplicateList = new ArrayList<>(clinicalDataList);
		
		for(ClinicalData eachEntry:duplicateList) {
			
			if(filter.containsKey(eachEntry.getComponentName())) {
				clinicalDataList.remove(eachEntry);
				continue;
			}else {
				filter.put(eachEntry.getComponentName(), eachEntry.getComponentName());
			}
			
			BMICalculator.calculateBMI(clinicalDataList, eachEntry);
			
			
		}
		filter.clear();
		return patient;
	}

}
