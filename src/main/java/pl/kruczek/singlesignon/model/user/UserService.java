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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        List<UserDto> collect = userRepository.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList());

        if (sq.isEmpty()) return collect;

        List<UserDto> result = new ArrayList<>();

        collect.forEach(u -> {
            checkData(sq, result, u);
        });

        return result;
    }

    private void checkData(SearchQueryUser sq, List<UserDto> result, UserDto u) {
        if (sq.getIds() != null) {
            if (sq.getIds().stream().anyMatch(uuid -> matchUser(uuid, "id", u))) result.add(u);
        }

        if (sq.getUsernames() != null) {
            if (sq.getUsernames().stream().anyMatch(username -> matchUser(username, "username", u))) result.add(u);
        }

        if (sq.getFirstnames() != null) {
            if (sq.getFirstnames().stream().anyMatch(firstname -> matchUser(firstname, "firstname", u))) result.add(u);
        }

        if (sq.getLastnames() != null) {
            if (sq.getLastnames().stream().anyMatch(lastname -> matchUser(lastname, "lastname", u))) result.add(u);
        }

        if (sq.getScores() != null) {
            if (sq.getScores().stream().anyMatch(score -> matchUser(score, "score", u))) result.add(u);
        }

        if (sq.getActives() != null) {
            if (sq.getActives().stream().anyMatch(active -> matchUser(active, "active", u))) result.add(u);
        }

        if (sq.getEmails() != null) {
            if (sq.getEmails().stream().anyMatch(email -> matchUser(email, "email", u))) result.add(u);
        }

        if (sq.getRoles() != null) {
            if (sq.getRoles().stream().anyMatch(roles -> matchUser(roles, "roles", u))) result.add(u);
        }
    }

    private boolean matchUser(String param, String kind, UserDto dto) {
        switch (kind) {
            case "id":
                return dto.getId().toString().equalsIgnoreCase(param);
            case "username":
                return dto.getUsername().equalsIgnoreCase(param);
            case "firstname":
                return dto.getFirstname().equalsIgnoreCase(param);
            case "lastname":
                return dto.getLastname().equalsIgnoreCase(param);
            case "score":
                return String.valueOf(dto.getScore()).equalsIgnoreCase(param);
            case "active":
                return String.valueOf(dto.isActive()).equalsIgnoreCase(param);
            case "email":
                return dto.getEmail().equalsIgnoreCase(param);
            case "roles":
                return dto.getRoles().contains(UserRole.valueOf(param));
            default:
                return false;
        }
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
