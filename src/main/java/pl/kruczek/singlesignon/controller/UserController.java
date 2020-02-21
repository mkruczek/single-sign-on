package pl.kruczek.singlesignon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.model.UserDto;
import pl.kruczek.singlesignon.model.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

//    @GetMapping(value = "/user/{id}")
//    public UserEntity getUser(@PathVariable("id") UUID id) {
//
//        UserEntity userEntity = userRepo.get(id);
//
//        if (userEntity == null) {
//            throw new UserNotFoundException(id.toString());
//        }
//
//        return userRepo.get(id);
//    }
}

