package pl.kruczek.singlesignon.model.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(UserEntity... entities);

    Optional<UserEntity> getUser(UUID id);

    Optional<UserEntity> getUser(String username);

    List<UserEntity> getUsers();

    void update(UUID id, UserEntity userEntity);

    void deleteUser(UUID id);

}
