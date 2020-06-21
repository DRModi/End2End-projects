package com.drmodi.clinicals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drmodi.clinicals.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
