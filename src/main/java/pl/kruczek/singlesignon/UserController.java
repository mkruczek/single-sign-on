package pl.kruczek.singlesignon;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.exception.UserNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {


    private static Map<UUID, User> userRepo = new HashMap<>();

    static {
        User mietek = new User();
        mietek.setId(UUID.randomUUID());
        mietek.setName("mietek");
        userRepo.put(mietek.getId(), mietek);

        User wacek = new User();
        wacek.setId(UUID.randomUUID());
        wacek.setName("wacek");
        userRepo.put(wacek.getId(), wacek);
    }

    @GetMapping(value = "/user")
    public Collection<User> getUsers() {
        return userRepo.values();
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") UUID id){

        User user = userRepo.get(id);

        if (user == null) {
            throw new UserNotFoundException(id.toString());
        }

        return userRepo.get(id);
    }
}

@Data
class User {
    UUID id;
    String name;
}