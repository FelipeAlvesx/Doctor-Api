package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.DataCreateAppointment;
import med.voll.api.domain.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientActive implements GlobalAppointmentValidate{

    @Autowired
    private PatientsRepository patientsRepository;

    public void validate(DataCreateAppointment data){
        var patient = patientsRepository.getActiveById(data.idPatient());

        if(!patient){
            throw new RuntimeException("Patient is not active");
        }
    }

}
