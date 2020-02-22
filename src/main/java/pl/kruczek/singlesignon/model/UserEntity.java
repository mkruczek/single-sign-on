package pl.kruczek.singlesignon.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEntity {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
    private String email;
    private String roles;
}
