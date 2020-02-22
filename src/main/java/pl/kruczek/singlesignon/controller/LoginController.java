package pl.kruczek.singlesignon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.model.jwt.JwtResponse;
import pl.kruczek.singlesignon.model.login.LoginBody;
import pl.kruczek.singlesignon.model.login.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginBody loginBody, HttpServletResponse response) {

        JwtResponse jwtResponse = loginService.loginUser(loginBody);

        Cookie cookie = new Cookie("cookie", "jwt=" + jwtResponse.getJwt());
        cookie.setMaxAge(500_000);
        response.addCookie(cookie);

        return ResponseEntity.ok("succes");

    }
}
