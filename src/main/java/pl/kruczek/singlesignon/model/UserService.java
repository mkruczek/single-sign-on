package pl.kruczek.singlesignon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.getUser(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not found user: " + username);
        }
        return new UserAuthentication(user.get());
    }

    public UserDto getUserById(UUID id) {
        return userRepository.getUser(id).map(UserDto::fromEntity).orElseThrow(() -> new UsernameNotFoundException("Not found user: " + id));
    }

    public List<UserDto> getUserByParams(Map<String, String> allParams) {

        if (allParams.isEmpty()) {
            return userRepository.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList());
        }

        return null;
    }
}
