package com.oc.mediscreen.repository;

import com.oc.mediscreen.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByFamilyAndGiven(String family, String given);
    Patient findByPhone(String phoneNumber);
    List<Patient> findByAddress(String adress);
    List<Patient> findByFamily(String family);
}
