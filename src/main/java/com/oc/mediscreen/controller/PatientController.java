package com.oc.mediscreen.controller;

import com.oc.mediscreen.entity.Patient;
import com.oc.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping()
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @GetMapping("/phone/{phone}")
    public Patient findByPhone(@PathVariable String phone) {
        return patientService.findByPhone(phone);
    }

    @GetMapping("/address/{address}")
    public List<Patient> findByAddress(@PathVariable String address) {
        return patientService.findByAddress(address);
    }

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Patient save(Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping("/patient/{firstName}/{lastName}")
    public void updatePatient(@RequestBody Patient patient, @PathVariable String firstName, @PathVariable String lastName) {
        patientService.update(patient, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }

}
