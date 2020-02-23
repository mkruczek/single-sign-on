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

import javax.crypto.SealedObject;
import java.util.Arrays;
import java.util.HashMap;
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

    @GetMapping(value = "/user/")
    public List<UserDto> getUser(@RequestParam Map<String,String> param) {
        return userService.getUsers(param);
    }

//    @GetMapping(value = "/user/")
//    public List<UserDto> getUser( @RequestParam(required = false) UUID id,
//                                  @RequestParam(required = false) String username,
//                                  @RequestParam(required = false) String firstname,
//                                  @RequestParam(required = false) String lastname,
//                                  @RequestParam(required = false) Integer score,
//                                  @RequestParam(required = false) Boolean active,
//                                  @RequestParam(required = false) String email,
//                                  @RequestParam(required = false) String roles) {
//
//        SealedQuery sq = new SearchQuery();
//        return userService.getUsers(sq);
//    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user/")
    public UserDto addUser(@RequestBody UserDto dto) {
//        return userService.addUser(dto);
        return null;
    }

    @PutMapping(value = "/user/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto dto){
//        return userService.updateUser(dto);
        return null;
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable String id){
//        userService.deleteUSer(id);
    }
}

