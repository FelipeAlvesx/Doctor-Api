package med.voll.api.infra.exception;

public class AppointmentValidateException extends RuntimeException {
    public AppointmentValidateException(String message) {
        super(message);
    }
}
