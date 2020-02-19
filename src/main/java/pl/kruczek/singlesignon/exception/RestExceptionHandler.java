package pl.kruczek.singlesignon.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( value = UserNotFoundException.class )
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException() {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(404)
                .message("UserNotFoundException")
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
