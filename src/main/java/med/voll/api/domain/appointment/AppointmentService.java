package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public void createAppointment(DataCreateAppointment dataCreateAppointment) {

        if(dataCreateAppointment.idDoctor() != null && !doctorRepository.existsById(dataCreateAppointment.idDoctor())){
            throw new AppointmentValidateException("Doctor not found");
        }
        if(!patientsRepository.existsById(dataCreateAppointment.idPatient())) {
            throw new AppointmentValidateException("Patient Not found");
        }

        var doctor = sortDoctor(dataCreateAppointment);
        var patient = patientsRepository.getReferenceById(dataCreateAppointment.idPatient());

        if(!patient.getActive()){
            throw new AppointmentValidateException("Patient is not active");
        }

        var appointment = new Appointment(null, doctor, patient, dataCreateAppointment.date());
        appointmentRepository.save(appointment);

    }

    private Doctor sortDoctor(DataCreateAppointment dataCreateAppointment) {
        if(dataCreateAppointment.idDoctor() != null){
            return doctorRepository.getReferenceById(dataCreateAppointment.idDoctor());
        }
        if (dataCreateAppointment.espec() == null){
            throw new AppointmentValidateException("Specialty is required when no doctor is selected");
        }

        return doctorRepository.findAvailableDoctors(dataCreateAppointment.date(), dataCreateAppointment.espec());

    }

}
