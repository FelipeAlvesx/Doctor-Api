package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.LoginData;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class doctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void login(@RequestBody @Valid LoginData data){
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public List<Doctor> readAll(){
        return doctorRepository.findAll();
    }

}
