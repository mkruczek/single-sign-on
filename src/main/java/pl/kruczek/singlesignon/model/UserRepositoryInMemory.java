package pl.kruczek.singlesignon.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryInMemory implements UserRepository {


    Map<UUID, UserEntity> mapDB = new HashMap<>();

    @Override
    public void save(UserEntity... entities) {
        Arrays.stream(entities).forEach( ue -> mapDB.put(ue.getId(), ue));
    }

    @Override
    public Optional<UserEntity> getUser(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> getUser(String login) {
        return mapDB.values().stream().filter(x -> x.getUsername().equalsIgnoreCase(login)).findAny();
    }

    @Override
    public List<UserEntity> getUsers() {
        return new ArrayList<>(mapDB.values());
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public void update(UUID id, UserEntity userEntity) {
    }
}
