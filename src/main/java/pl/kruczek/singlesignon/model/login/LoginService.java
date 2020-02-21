package pl.kruczek.singlesignon.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kruczek.singlesignon.exception.UserNotFoundException;
import pl.kruczek.singlesignon.model.UserEntity;
import pl.kruczek.singlesignon.model.UserRepository;

import java.util.Optional;

@Service
public class LoginService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean loginUser(LoginBody loginBody){

        Optional<UserEntity> user = userRepository.getUser(loginBody.getLogin());

        if (user.isEmpty()) {
            throw new UserNotFoundException("lalala");
        }

        boolean matches = passwordEncoder.matches(user.get().getPassword(), loginBody.getPassword());

        return user.get().getPassword().equalsIgnoreCase(loginBody.getPassword());

    }
}
