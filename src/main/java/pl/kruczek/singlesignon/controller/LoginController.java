package pl.kruczek.singlesignon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.model.login.LoginBody;
import pl.kruczek.singlesignon.model.login.LoginService;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @PostMapping(value = "/login")
//    public boolean login(@RequestBody LoginBody loginBody){
//        return loginService.loginUser(loginBody);
//    }



}
