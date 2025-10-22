package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Espec;

import java.time.LocalDateTime;

public record DataCreateAppointment(Long idDoctor, Long idPatient, LocalDateTime date, Espec espec){


}
