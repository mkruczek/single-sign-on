package pl.kruczek.singlesignon.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kruczek.singlesignon.config.jwt.JwtService;
import pl.kruczek.singlesignon.exception.UserNotFoundException;
import pl.kruczek.singlesignon.model.UserService;
import pl.kruczek.singlesignon.model.jwt.JwtResponse;

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

        UserDetails userDetails = userService.loadUserByUsername(loginBody.getUsername());

        if (userDetails == null) {
            throw new UserNotFoundException("User Not Found: " + loginBody.getUsername());
        } else if (!userDetails.isEnabled()) {
            throw new UserNotFoundException("User No Active");//todo make other exception
        }

        if (!passwordEncoder.matches(loginBody.getPassword(), userDetails.getPassword())){
            throw new UserNotFoundException("You not shall pass");//todo make other exception
        }

        return new JwtResponse(jwtService.generateToken(userDetails));
    }
}
