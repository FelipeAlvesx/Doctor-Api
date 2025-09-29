package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /* Identify Error and call this function */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> notFoundException(){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequestException(MethodArgumentNotValidException exception){
        var error = exception.getFieldErrors(); /* -> Rewtorna uma lista de erros */

        /* Esta sendo convertido para um dto com o campo e a message de erro */
        return ResponseEntity.badRequest().body(error.stream().map(DataValidationException::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity Error400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity ErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity ErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR ON AUTHENTICATION");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity ErrorAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ACCESS DENIED");

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


    /* DTO */
    private record DataValidationException(String field, String message){

        public DataValidationException(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
