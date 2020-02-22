package pl.kruczek.singlesignon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.kruczek.singlesignon.model.UserDto.fromEntity;

@Service
public class UserService  implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.getUser(s);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("Not faund user: " + s);
        }
        return new UserAuthentication(user.get());
    }
}
