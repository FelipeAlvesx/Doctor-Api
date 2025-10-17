package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public void createAppointment(DataCreateAppointment dataCreateAppointment) {

        var doctor = doctorRepository.findById(dataCreateAppointment.idDoctor())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        if(!doctor.getActive()){
            throw new RuntimeException("Doctor is not active");
        }


        var patient = patientsRepository.findById(dataCreateAppointment.idPatient())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        if(!patient.getActive()){
            throw new RuntimeException("Patient is not active");
        }

        var appointment = new Appointment(null, doctor, patient, dataCreateAppointment.date());
        appointmentRepository.save(appointment);

    }

}
