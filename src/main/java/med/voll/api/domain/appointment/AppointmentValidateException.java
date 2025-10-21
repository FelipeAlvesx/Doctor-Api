package med.voll.api.domain.appointment;

public class AppointmentValidateException extends RuntimeException {
    public AppointmentValidateException(String message) {
        super(message);
    }
}
