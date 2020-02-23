package pl.kruczek.singlesignon.model.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kruczek.singlesignon.exception.exceptions.ValidateUserException;

import java.util.List;
import java.util.Optional;

@Component
public class UserValidator {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository repository;

    @Autowired
    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    public void validateUser(UserDto dto) {

        boolean validateUsername = validateUsername(dto.getUsername());
        boolean validatePassword = validatePassword(dto.getPassword());
        boolean validateLastname = validateLastname(dto.getLastname());
        boolean validateRoles = validateRoles(dto.getRoles());

        if (validateUsername&&validatePassword&&validateLastname&&validateRoles){
            logger.info("User body is valid: " + dto.getUsername());
        }
    }

    private boolean validatePassword(String password) {
        if (password == null || password.equalsIgnoreCase("")) {
            throw new ValidateUserException("Password is required.");
        }
        return true;
    }

    private boolean validateUsername(String username) {

        if (username == null || username.equalsIgnoreCase("")) {
            throw new ValidateUserException("Username is required.");
        }

        Optional<UserEntity> user = repository.getUser(username);
        if (user.isPresent()) {
            throw new ValidateUserException("Username already exist.");
        }
        return true;
    }

    private boolean validateLastname(String lastname) {
        if (lastname == null || lastname.equalsIgnoreCase("")) {
            throw new ValidateUserException("Lastname is required.");
        }
        return true;
    }

    private boolean validateRoles(List<UserRole> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new ValidateUserException("At least one role required.");
        }
        return true;
    }
}
