package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.Espec;

import java.time.LocalDateTime;

public record DetailsAppointment(Long id, Long idDoctor, Long idPatient, LocalDateTime dateTime, Espec espec) {

    public DetailsAppointment(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatients().getId(), appointment.getDate(), appointment.getDoctor().getEspec());
    }
}
