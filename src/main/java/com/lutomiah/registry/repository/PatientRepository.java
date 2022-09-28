package com.lutomiah.registry.repository;

import com.lutomiah.registry.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmailId(String email);
}
