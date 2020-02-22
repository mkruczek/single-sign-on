package pl.kruczek.singlesignon.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( value = UsernameNotFoundException.class )
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    //todo add rest exceptions
}
