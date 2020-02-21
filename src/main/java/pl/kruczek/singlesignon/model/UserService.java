package pl.kruczek.singlesignon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static pl.kruczek.singlesignon.model.UserDto.fromEntity;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public UserEntity addUser(UserDto dto){
////        boolean isValid = validate(user);
//
//        user.setId();
//
//
//    }

    public List<UserDto> getUsers() {
        return userRepository.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }
}
