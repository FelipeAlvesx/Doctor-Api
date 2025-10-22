package med.voll.api.domain.appointment;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);

    boolean existsByPatientsIdAndDateBetween(Long id, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
}
