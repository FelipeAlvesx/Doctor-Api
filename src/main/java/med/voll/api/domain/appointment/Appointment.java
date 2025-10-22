package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patients.Patients;

@Entity(name = "Appointment")
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_id")
    private Patients patients;

    private LocalDateTime date;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment(Doctor doctor, Patients patients, LocalDateTime date) {
        this.doctor = doctor;
        this.patients = patients;
        this.date = date;
        this.status = AppointmentStatus.SCHEDULED;
        this.cancellationReason = null;
    }

    public void updateStatus(DataCancelAppointment dataCancelAppointment) {
        this.status = AppointmentStatus.CANCELED;
        this.cancellationReason = dataCancelAppointment.cancelletionReason();
    }
    
}
