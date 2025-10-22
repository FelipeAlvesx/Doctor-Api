package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.DataCreateAppointment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateTimeOperation implements GlobalAppointmentValidate{

    public void validate(DataCreateAppointment data){

        var dateAppointment = data.date();
        var hour = dateAppointment.getHour();
        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if(hour < 7 || hour > 18 || sunday){
            throw new RuntimeException("Appointments can only be scheduled between 7 AM and 6 PM, Sundey Is Closed");
        }
    }

}
