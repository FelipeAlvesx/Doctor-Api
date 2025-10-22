package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataCreateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientAppointmentAtDate implements GlobalAppointmentValidate{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(DataCreateAppointment data) {

        var firstAppointment = data.date().withHour(7);
        var lastAppointment = data.date().withHour(18);

        var patientHaveAppointmentAtDate = appointmentRepository.existsByPatientsIdAndDateBetween(data.idPatient(), firstAppointment, lastAppointment);

        if(patientHaveAppointmentAtDate){
            throw new RuntimeException("Patient already has an appointment on this date");
        }
    }

}
