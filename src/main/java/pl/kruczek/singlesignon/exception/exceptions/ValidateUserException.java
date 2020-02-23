package pl.kruczek.singlesignon.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidateUserException extends RuntimeException {
    public ValidateUserException(String message) {
        super(message);
    }
}
