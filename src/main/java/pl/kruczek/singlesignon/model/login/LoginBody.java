package pl.kruczek.singlesignon.model.login;

import lombok.Data;

@Data
public class LoginBody {

    private String login;
    private String password;
}
