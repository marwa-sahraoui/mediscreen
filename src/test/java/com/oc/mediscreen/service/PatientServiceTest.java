package com.oc.mediscreen.service;

import com.oc.mediscreen.entity.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @BeforeAll
    public void patientInit() {
        patientService.deleteAll();
        Patient patient = new Patient();
        patient.setFamily("TestNone");
        patient.setGiven("Test");
        patient.setDob("1966-12-31");
        patient.setSex("F");
        patient.setAddress("Brookside St");
        patient.setPhone("100-222-99");
        patientService.save(patient);
    }


    @Test()
    //on verifie que la liste des patients n'est pas vide
    void findAll() {
        List<Patient> listResult = patientService.findAll();
        assertTrue(listResult.size() > 0);
    }

    @Test
    void findById() {
        //On extrait le premier patient de la liste des patients (firstPatient)
        //On extrait son id
        List<Patient> patients = patientService.findAll();
        assertFalse(patients.isEmpty());

        Patient firstPatient = patients.get(0);
        Long id = firstPatient.getId();
        //On utilise find by Id pour extraire un patient
        //puis on s'assure que le patient et le firstPatient tous les 2 ayant les mêmes attributs
        Patient patient = patientService.findById(id);
        Assert.assertEquals(firstPatient.getFamily(), patient.getFamily());
        Assert.assertEquals(firstPatient.getGiven(), patient.getGiven());
        Assert.assertEquals(firstPatient.getDob(), patient.getDob());
        Assert.assertEquals(firstPatient.getPhone(), patient.getPhone());
        Assert.assertEquals(firstPatient.getSex(), patient.getSex());
        Assert.assertEquals(firstPatient.getAddress(), patient.getAddress());
    }

    @Test
    void findByPhone() {
        //On vérifie pour le patient ayant un numero  du telephone donné s'apppelle Test
        Patient patient = patientService.findByPhone("100-222-99");
        Assert.assertEquals(patient.getGiven(), "Test");
    }

    @Test
    void findByPhoneNotExisted() {
        //On vérifie pour un numéro du téléphone non existant qu'on n'a pas du patient
        Patient patient = patientService.findByPhone("17-18-011");
        Assert.assertNull(patient);
    }

    @Test
    void findByAddress() {
        //On ajoute un 2 eme patient habitant à la même addresse que le premier et on vérifie pour
        //qu 'cette adresse  existe 2 patients
        Patient patient2 = new Patient();
        patient2.setFamily("jones");
        patient2.setGiven("don");
        patient2.setDob("2001-10-01");
        patient2.setSex("F");
        patient2.setAddress("Brookside St");
        patient2.setPhone("12-13-002");
        patientService.save(patient2);
        List<Patient> patients = patientService.findByAddress("Brookside St");
        Assert.assertTrue(patients.size() == 2);

    }

    @Test
    void findByAddressWhereNoAddressExist() {
        //On vérifie pour une adresse non existante qu'il n'existe pas de patient
        List<Patient> patients = patientService.findByAddress("Colombes");
        Assert.assertTrue(patients.size() == 0);
    }

    @Test
    void deleteById() {
        //On ajoute un patientX, On l 'enregistre puis on le supprime (deleteById)
        //puis on appelle le findBy Id pour l'id du patientX
        //On vérifie que le patient est null bien sur en prenant en considération l'exception
        Patient patientX = new Patient("Peter", "Bob", "1988-20-20", "F", "Poissy", "29-111-0101");
        patientService.save(patientX);
        patientService.deleteById(patientX.getId());

        Patient patient = null;
        try {
            patient = patientService.findById(patientX.getId());
        } catch (Exception e) {
            System.out.println("Patient not found");
        }
        Assert.assertTrue(patient == null);
    }

    @Test
    void update() {
        //On extrait le premier patient (first Patient) de la liste des patients
        //On modifie son nom de famille (albert) puis on vérifie que son nom de famille n'est plus
        //l'ancien(TestNone) mais il s'agit bien de "Albert"
        List<Patient> patients = patientService.findAll();
        Patient firstPatient = patients.get(0);
        firstPatient.setFamily("Albert");
        patientService.update(firstPatient, patients.get(0).getId());
        Assert.assertEquals("Albert", firstPatient.getFamily());
        Assert.assertFalse(firstPatient.getFamily().equals("TestNone"));
    }

    @AfterAll
    public void deleteAll() {
        patientService.deleteAll();
    }
}
