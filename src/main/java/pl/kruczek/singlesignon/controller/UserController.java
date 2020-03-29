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
import pl.kruczek.singlesignon.model.user.SearchQueryUser;
import pl.kruczek.singlesignon.model.user.UserDto;
import pl.kruczek.singlesignon.model.user.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/")
    public List<UserDto> getUser(@RequestParam(required = false) String id,
                                 @RequestParam(required = false) String username,
                                 @RequestParam(required = false) String firstname,
                                 @RequestParam(required = false) String lastname,
                                 @RequestParam(required = false) String score,
                                 @RequestParam(required = false) String active,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String roles) {

        SearchQueryUser sq = new SearchQueryUser(id, username, firstname, lastname, score, active, email, roles);
        return userService.getUsers(sq);
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user/")
    public UserDto addUser(@RequestBody UserDto dto) {
        return userService.addUser(dto);
    }

    @PutMapping(value = "/user/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}

