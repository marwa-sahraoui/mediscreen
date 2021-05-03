package com.oc.mediscreen.service;

import com.oc.mediscreen.entity.Patient;
import com.oc.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
    }

    public Patient findByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    public List<Patient> findByAddress(String address) {
        return patientRepository.findByAddress(address);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public void update(Patient patient, Long id) {

        Optional<Patient> oldPatientOptional = patientRepository.findById(id);

        if (oldPatientOptional.isPresent()) {
            Patient oldPatient = oldPatientOptional.get();

            oldPatient.setFamily(patient.getFamily());
            oldPatient.setFamily(patient.getFamily());
            oldPatient.setGiven(patient.getGiven());
            oldPatient.setDob(patient.getDob());
            oldPatient.setSex(patient.getSex());
            oldPatient.setAddress(patient.getAddress());
            oldPatient.setPhone(patient.getPhone());
            patientRepository.save(patient);
        } else {
            throw new IllegalStateException("Patient not found !!!!!");
        }
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }

    public List<Patient> findByFamily(String family) {
        return patientRepository.findByFamily(family);
    }
}
