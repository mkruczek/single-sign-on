package pl.kruczek.singlesignon.model;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserEntity {
    private UUID id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int score;
    private boolean active;
    private String email;
    private String roles;

    public List<String> splitRoles() {
        return Arrays.asList(this.getRoles().split(";"));
    }
}
