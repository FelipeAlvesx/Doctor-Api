package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailsAppointment(Long id, Long idDoctor, Long idPatient, LocalDateTime dateTime) {

}
