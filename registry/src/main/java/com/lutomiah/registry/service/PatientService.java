package com.lutomiah.registry.service;

import com.lutomiah.registry.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient user);
    Patient getPatientByClientIdAndEmail(String email);
    List<Patient> getPatients();
}
