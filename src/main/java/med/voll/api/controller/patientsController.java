package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.patients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<PatientsResponseData> readAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return patientsRepository.findAll(pageable).map(PatientsResponseData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientsUpdateData patientsUpdateData){
        var patient = patientsRepository.getReferenceById(patientsUpdateData.id());
        patient.updatePatient(patientsUpdateData);
    }

}
