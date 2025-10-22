package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.DataCreateAppointment;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorActive implements GlobalAppointmentValidate{

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(DataCreateAppointment data){

        if(data.idDoctor() == null){
            return;
        }

        var doctor = doctorRepository.getActiveById(data.idDoctor());

        if(!doctor){
            throw new RuntimeException("Doctor is not active");
        }

    }

}
