package pl.kruczek.singlesignon.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.kruczek.singlesignon.model.UserEntity.allowedParamsToSearch;

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
        if (user.isEmpty()) throw new UsernameNotFoundException("Not found user: " + username);
        return new UserAuthentication(user.get());
    }

    public UserDto getUserById(UUID id) {
        return userRepository.getUser(id).map(UserDto::fromEntity).orElseThrow(() -> new UsernameNotFoundException("Not found user: " + id));
    }

    public List<UserDto> getUsers(Map<String, String> param) {

        if (param.isEmpty())
            return userRepository.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList());

        if (!paramIsProperly(param)) throw new RuntimeException("ParamNotSupported"); // todo paramNotSupportedException

        List<UserDto> result = new ArrayList<>();

        param.keySet().forEach( kind -> {
            if (param.get(kind).contains(",")){
                List<String> queryParams = Arrays.asList(param.get(kind).split(","));
                queryParams.forEach(p ->{
                    result.addAll(matchUsers(p, kind));
                });
            } else {
                result.addAll(matchUsers(param.get(kind), kind));
            }
        });
        return result;
    }

    private List<UserDto> matchUsers(String param, String kind) {
        return userRepository.getUsers().stream().filter(x -> {
            switch (kind) {
                case "id":
                    return x.getId().toString().equalsIgnoreCase(param);
                case "username":
                    return x.getUsername().equalsIgnoreCase(param);
                case "firstname":
                    return x.getFirstname().equalsIgnoreCase(param);
                case "lastname":
                    return x.getLastname().equalsIgnoreCase(param);
                case "score":
                    return String.valueOf(x.getScore()).equalsIgnoreCase(param);
                case "active":
                    return String.valueOf(x.isActive()).equalsIgnoreCase(param);
                case "email":
                    return x.getEmail().equalsIgnoreCase(param);
                case "roles":
                    return x.splitRoles().contains(param);
                default:
                    return false;
            }
        })
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    private boolean paramIsProperly(Map<String, String> param) {
        return param.keySet().stream().anyMatch(k -> {
            return allowedParamsToSearch().stream().anyMatch(p -> p.equalsIgnoreCase(k));
        });
    }
}
