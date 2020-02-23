package pl.kruczek.singlesignon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserValidator {

    private UserRepository repository;

    @Autowired
    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    public void validateUser(UserDto dto) {

        validateUsername(dto.getUsername());
        validateLastname(dto.getLastname());
        validateRoles(dto.getRoles());

//        boolean validUsername = validateUsername(dto.getUsername());
//        boolean validLastname = validateLastname(dto.getLastname());
//        boolean validRoles = validateRoles(dto.getRoles());

//        return validUsername && validLastname && validRoles;
    }

    private boolean validateUsername(String username) {

        if (username ==null || username.equalsIgnoreCase("")){
            throw new RuntimeException("USERNAME IS REQUIRED"); //todo properly
        }

        Optional<UserEntity> user = repository.getUser(username);
        if (user.isPresent()) {
            throw new RuntimeException("username alredy exit"); //todo properly exceprion and validation too
        }
        return true;
    }

    private boolean validateLastname(String lastname) {
        if (lastname == null || lastname.equalsIgnoreCase("")) {
            throw new RuntimeException("Last name is required"); //todo properly exceprion and validation too
        }
        return true;
    }

    private boolean validateRoles(List<UserRole> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("at least one role required"); //todo properly exceprion and validation too
        }
        return true;
    }

}
