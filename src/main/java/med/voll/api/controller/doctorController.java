package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class doctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void login(@RequestBody @Valid DoctorLoginData data){
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorResponseData> readAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return doctorRepository.findAll(pageable).map(DoctorResponseData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData doctorUpdateData){
        var doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateDoctor(doctorUpdateData);
    }


}
