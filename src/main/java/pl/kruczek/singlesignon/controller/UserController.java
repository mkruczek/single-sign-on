package pl.kruczek.singlesignon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.model.UserDto;
import pl.kruczek.singlesignon.model.UserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/") //todo make one value in @RestController
    public List<UserDto> getUser(@RequestParam Map<String,String> allParams) {
        return userService.getUserByParams(allParams);
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user/") //todo
    public UserDto addUser(@RequestBody UserDto dto) {
//        return userService.addUser(dto);
        return null;
    }

    @PutMapping(value = "/user/{id}") //todo
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto dto){
//        return userService.updateUser(dto);
        return null;
    }

    @DeleteMapping(value = "/user/{id}") //todo
    public void deleteUser(@PathVariable String id){
//        userService.deleteUSer(id);
    }
}

