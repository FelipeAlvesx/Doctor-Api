package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.DataCreateAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ValidateTimeAdvance implements GlobalAppointmentValidate{


    public void validate(DataCreateAppointment data){

        var now =  java.time.LocalDateTime.now();
        var dateAppointment = data.date();
        var difference = Duration.between(now, dateAppointment).toMinutes();

        if (difference < 30) {
            throw new RuntimeException("Appointments must be scheduled at least 30 minutes in advance");
        }


    }

}
