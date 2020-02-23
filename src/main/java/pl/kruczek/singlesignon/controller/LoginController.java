package pl.kruczek.singlesignon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.model.jwt.JwtResponse;
import pl.kruczek.singlesignon.model.login.LoginBody;
import pl.kruczek.singlesignon.model.login.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginBody loginBody, HttpServletResponse response) {

        JwtResponse jwtResponse = loginService.loginUser(loginBody);
        response.addCookie(generateCookie(jwtResponse.getJwt()));
        return ResponseEntity.ok("succes");
    }

    private Cookie generateCookie(String jwt){
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(24*60*60);
        return cookie;
    }
}

