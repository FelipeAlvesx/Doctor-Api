package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patients.Patients;
import med.voll.api.patients.PatientsData;
import med.voll.api.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class patientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @PostMapping
    @Transactional
    public void login(@RequestBody @Valid PatientsData patientsData){
        patientsRepository.save(new Patients(patientsData));
    }

}
