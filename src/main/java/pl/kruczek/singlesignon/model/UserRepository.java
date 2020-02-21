package pl.kruczek.singlesignon.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    public void save(UserEntity userEntity);

    public Optional<UserEntity> getUser(UUID id);

    public Optional<UserEntity> getUser(String login);

    public List<UserEntity> getUsers();

    public void deleteUser(UUID id);

    public void update(UUID id, UserEntity userEntity);
}
