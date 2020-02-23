package pl.kruczek.singlesignon.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int score;
    private String email;
    private List<UserRole> roles;

    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .score(entity.getScore())
                .email(entity.getEmail())
                .roles(entity.splitRoles().stream().map(UserRole::valueOf).collect(Collectors.toList()))
                .build();
    }
}