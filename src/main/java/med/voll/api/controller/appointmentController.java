package med.voll.api.controller;

import java.time.LocalDateTime;

import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DataCancelAppointment;
import med.voll.api.domain.appointment.DataCreateAppointment;
import med.voll.api.domain.appointment.DetailsAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("appointments")
public class appointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid DataCreateAppointment data){

        var dto = appointmentService.createAppointment(data);
        return ResponseEntity.ok(dto);

    }


    @PostMapping("/cancel")
    @Transactional
    public ResponseEntity<?> cancel(@RequestBody @Valid DataCancelAppointment data){

        appointmentService.cancelAppointment(data);
        return ResponseEntity.noContent().build();

    }


    
}
