package pl.kruczek.singlesignon.model.user;

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
    private boolean active;
    private String email;
    private List<UserRole> roles;

    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .score(entity.getScore())
                .active(entity.isActive())
                .email(entity.getEmail())
                .roles(entity.splitRoles().stream().map(UserRole::valueOf).collect(Collectors.toList()))
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(this.id != null ? this.id : UUID.randomUUID())
                .username(this.username)
                .password(this.password)
                .firstname(this.username)
                .lastname(this.lastname)
                .score(this.score)
                .active(this.active)
                .email(this.email)
                .roles(handleRoles(this.roles))
                .build();
    }

    private String handleRoles(List<UserRole> roles) {
        StringBuilder sb = new StringBuilder();
        roles.forEach(r -> sb.append(r + ";"));
        sb.deleteCharAt(sb.lastIndexOf(";"));
        return sb.toString();
    }
}