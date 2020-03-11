package pl.kruczek.singlesignon.model.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kruczek.singlesignon.exception.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private UserValidator userValidator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.getUser(username);
        if (user.isEmpty()) {
            logger.info("Not found user: " + username);
            throw new UsernameNotFoundException("Not found user: " + username);
        }
        return new UserAuthentication(user.get());
    }

    public UserDto getUserById(UUID id) {
        return userRepository.getUser(id).map(UserDto::fromEntity).orElseThrow(() -> new UserNotFoundException("Not found user: " + id));
    }

    public List<UserDto> getUsers(SearchQueryUser sq) {

        if (sq.isEmpty()) {
            return userRepository.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList());
        }

        Predicate<UserDto> username = u -> sq.getUsernames().isEmpty() || sq.getUsernames().contains(u.getUsername());
        Predicate<UserDto> id = u -> sq.getIds().isEmpty() || sq.getIds().contains(String.valueOf(u.getId()));
        Predicate<UserDto> score = u -> sq.getScores().isEmpty() || sq.getScores().contains(String.valueOf(u.getScore()));
        Predicate<UserDto> email = u -> sq.getEmails().isEmpty() || sq.getEmails().contains(u.getEmail());
        Predicate<UserDto> firstname = u -> sq.getFirstnames().isEmpty() || sq.getFirstnames().contains(u.getFirstname());
        Predicate<UserDto> lastname = u -> sq.getLastnames().isEmpty() || sq.getLastnames().contains(u.getLastname());
        Predicate<UserDto> active = u -> sq.getActives().isEmpty() || sq.getActives().contains(String.valueOf(u.isActive()));
        Predicate<UserDto> roles = u -> sq.getRoles().isEmpty() || sq.getRoles().stream().anyMatch(r -> {
            return u.getRoles().stream().anyMatch(ur -> ur.toString().equalsIgnoreCase(r)) ;
        });

        return userRepository.getUsers().stream().map(UserDto::fromEntity)
                .collect(Collectors.toList())
                .stream().filter(username
                        .and(id)
                        .and(score)
                        .and(email)
                        .and(firstname)
                        .and(lastname)
                        .and(active)
                        .and(roles)
                ).collect(Collectors.toList());
    }

    public UserDto addUser(UserDto dto) {

        userValidator.validateUser(dto);

        UserEntity entityToSave = dto.toEntity();
        entityToSave.setPassword(passwordEncoder.encode(entityToSave.getPassword()));
        userRepository.save(entityToSave);

        logger.info("Added user: " + entityToSave.getId());

        return UserDto.fromEntity(entityToSave);
    }

    public UserDto updateUser(UUID id, UserDto dto) {

        UserEntity old = userRepository.getUser(id).orElseThrow(() -> new UserNotFoundException("Not found user: " + id));
        userValidator.validateUser(dto);

        dto.setId(old.getId());
        UserEntity entityToUpdate = dto.toEntity();
        userRepository.update(id, entityToUpdate);
        logger.info("Update user: " + id);
        return dto;
    }

    public void deleteUSer(UUID id) {
        userRepository.deleteUser(id);
        logger.info("Deleted user: " + id);
    }
}
