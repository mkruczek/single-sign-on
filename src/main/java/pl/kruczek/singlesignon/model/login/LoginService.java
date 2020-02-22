package pl.kruczek.singlesignon.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kruczek.singlesignon.config.jwt.JwtService;
import pl.kruczek.singlesignon.exception.UserNotFoundException;
import pl.kruczek.singlesignon.model.UserRole;
import pl.kruczek.singlesignon.model.UserService;
import pl.kruczek.singlesignon.model.jwt.JwtResponse;

import java.security.AccessControlException;
import java.util.stream.Collectors;

@Service
public class LoginService {

    UserService userService;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    @Autowired
    public LoginService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public JwtResponse loginUser(LoginBody loginBody) {

        UserDetails user = userService.loadUserByUsername(loginBody.getLogin());

        if (user == null) {
            throw new UserNotFoundException("lalala");
        }

        boolean matches = passwordEncoder.matches(loginBody.getPassword(), user.getPassword());

        if (!matches){
            throw new AccessControlException("You not shal pass");
        }

//        return user.getAuthorities().stream().map( r -> UserRole.valueOf(r.toString())).collect(Collectors.toList());

        return new JwtResponse(jwtService.generateToken(user));
    }
}
