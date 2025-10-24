package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patients.Patients;
import med.voll.api.domain.patients.PatientsLoginData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("test doctor whent not available in given date")
    void findAvailableDoctorsWhenNotAvailable() {

        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = cadastrarMedico("Dr. Disponivel", "doctor@gmail.com", "71997071909", "12345", Espec.CARDIOLOGY);
        var patient = cadastrarPaciente("Paciente", "patient@gmail.com", "12345678900", "71997071909");

        cadastrarConsulta(doctor, patient, nextMonday);

        var doctorAvailable = doctorRepository.findAvailableDoctors(nextMonday, Espec.CARDIOLOGY);
        assertThat(doctorAvailable).isNull();
    }


    private void cadastrarConsulta(Doctor doctor, Patients patients, LocalDateTime data) {
        em.persist(new Appointment(doctor, patients, data));
    }

    private Doctor cadastrarMedico(String name, String email,String phone ,String crm, Espec espec) {
        var medico = new Doctor(dadosMedico(name, email, crm, espec));
        em.persist(medico);
        return medico;
    }

    private Patients cadastrarPaciente(String name, String email, String cpf, String phone) {
        var paciente = new Patients(PatientData(name, email, cpf, phone, DataAddress()));
        em.persist(paciente);
        return paciente;
    }

    private DoctorLoginData dadosMedico(String name, String email, String crm, Espec espec) {
        return new DoctorLoginData(
                null,
                name,
                email,
                "61999999999",
                crm,
                espec,
                DataAddress()
        );
    }

    private PatientsLoginData PatientData(String name, String email, String cpf, String phone, AddressData addressData) {
        return new PatientsLoginData(
                name,
                email,
                cpf,
                cpf,
                DataAddress()
        );
    }

    private AddressData DataAddress() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }



}