package pl.kruczek.singlesignon.model.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@Document
public class User {
    @Id
    private String id;
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
