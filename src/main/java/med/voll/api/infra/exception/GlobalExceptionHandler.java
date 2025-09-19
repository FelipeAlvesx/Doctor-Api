package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /* Identify Error and call this function */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundException(){

        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequestException(MethodArgumentNotValidException exception){
        var error = exception.getFieldErrors(); /* -> Retorna uma lista de erros */

        /* Esta sendo convertido para um dto com o campo e a message de erro */
        return ResponseEntity.badRequest().body(error.stream().map(DataValidationException::new).toList());
    }

    /* DTO */
    private record DataValidationException(String field, String message){

        public DataValidationException(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
