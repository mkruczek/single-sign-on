package pl.kruczek.singlesignon.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kruczek.singlesignon.exception.exceptions.AddressNotFoundException;
import pl.kruczek.singlesignon.exception.exceptions.UserNotActiveException;
import pl.kruczek.singlesignon.exception.exceptions.UserNotFoundException;
import pl.kruczek.singlesignon.exception.exceptions.UserWrongPasswordException;
import pl.kruczek.singlesignon.exception.exceptions.ValidateUserException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( value = UsernameNotFoundException.class )
    public ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( value = UserNotActiveException.class )
    public ResponseEntity<ExceptionResponse> handleUserNotActiveException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(401)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler( value = UserNotFoundException.class )
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( value = UserWrongPasswordException.class )
    public ResponseEntity<ExceptionResponse> handleUserWrongPasswordException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(401)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler( value = ValidateUserException.class )
    public ResponseEntity<ExceptionResponse> handleValidateUserException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(400)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( value = AddressNotFoundException.class )
    public ResponseEntity<ExceptionResponse> handleAddressNotFoundException(Exception ex) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(401)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ExceptionResponse>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


}
