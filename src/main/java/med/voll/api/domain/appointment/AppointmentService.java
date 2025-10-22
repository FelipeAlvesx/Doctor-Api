package med.voll.api.domain.appointment;

import jakarta.validation.Valid;
import med.voll.api.domain.appointment.validations.GlobalAppointmentValidate;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patients.PatientsRepository;
import med.voll.api.infra.exception.AppointmentValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private List<GlobalAppointmentValidate> globalAppointmentValidates;

    public DetailsAppointment createAppointment(DataCreateAppointment dataCreateAppointment) {

        if(dataCreateAppointment.idDoctor() != null && !doctorRepository.existsById(dataCreateAppointment.idDoctor())){
            throw new AppointmentValidateException("Doctor not found");
        }
        if(!patientsRepository.existsById(dataCreateAppointment.idPatient())) {
            throw new AppointmentValidateException("Patient Not found");
        }

        var patient = patientsRepository.getReferenceById(dataCreateAppointment.idPatient());
        var doctor = sortDoctor(dataCreateAppointment);
        if(doctor == null){
            throw new AppointmentValidateException("No doctor available at this date");
        }


        globalAppointmentValidates.forEach(v -> v.validate(dataCreateAppointment));

        var appointment = new Appointment(doctor, patient, dataCreateAppointment.date());
        appointmentRepository.save(appointment);

        return new DetailsAppointment(appointment);

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

    public void cancelAppointment(@Valid DataCancelAppointment data) {

        var appointment = appointmentRepository.findById(data.appointmentId())
                .orElseThrow(() -> new AppointmentValidateException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.CANCELED) {
            throw new AppointmentValidateException("Appointment is already canceled");
        }

        if(appointment.getStatus() == AppointmentStatus.DONE) {
            throw new AppointmentValidateException("Completed appointments cannot be canceled");
        }

        if(appointment.getDate().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new AppointmentValidateException("24 hours notice is required to cancel an appointment");
        }

        appointment.updateStatus(data);
        appointmentRepository.save(appointment);

    }
}
