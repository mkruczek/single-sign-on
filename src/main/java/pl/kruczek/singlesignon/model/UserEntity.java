package pl.kruczek.singlesignon.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

@Data
@Builder
public class UserEntity {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
