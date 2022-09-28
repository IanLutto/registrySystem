package com.lutomiah.registry.service;

import com.lutomiah.registry.entity.Patient;
import com.lutomiah.registry.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient user) {
        return patientRepository.save(user);
    }

    @Override
    public Patient getPatientByClientIdAndEmail(String email) {
        log.info("Find Patient by email {} ", email);
        return patientRepository.findByEmailId(email);
    }

    @Override
    public List<Patient> getPatients() {
        log.info("Get all Patients from the database");
        return patientRepository.findAll();
    }
}
