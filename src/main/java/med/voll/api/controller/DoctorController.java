package med.voll.api.controller;

import med.voll.api.doctor.DataLogin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @PostMapping
    public void login(@RequestBody DataLogin data){
        System.out.println(data);
    }
}
