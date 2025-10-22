package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataCreateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorAnotherAppoint implements GlobalAppointmentValidate{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(DataCreateAppointment data) {

        var doctorAnotherAppoint = appointmentRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if(doctorAnotherAppoint){
            throw new RuntimeException("Doctor already has an appointment at this date and time");
        }

    }

}
