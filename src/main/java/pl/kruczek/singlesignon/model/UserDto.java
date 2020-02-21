package pl.kruczek.singlesignon.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword()) //todo delete
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }
}