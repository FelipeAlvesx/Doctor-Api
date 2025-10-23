package med.voll.api.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);


    @Query("""
               select d from Doctor d
                            where
                            d.active = true
                            and
                            d.espec = :espec
                            and
                            d.id not in(
                                    select a.doctor.id from Appointment a
                                    where
                                    a.date = :date
                                    and
                                    a.cancellationReason is null
                            )
                            order by rand()
                            limit 1
            """)
    Doctor findAvailableDoctors(LocalDateTime date, Espec espec);

    @Query("""
            select d.active from Doctor d
            where d.id = :id
            """)
    boolean getActiveById(@NotNull Long id);
}
