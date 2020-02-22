package pl.kruczek.singlesignon.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kruczek.singlesignon.model.UserEntity;
import pl.kruczek.singlesignon.model.UserRepository;
import pl.kruczek.singlesignon.model.UserRole;

import java.util.Arrays;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        UserEntity b = UserEntity.builder()
                .id(UUID.randomUUID())
                .username("user")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("janusz")
                .lastName("kowalski")
                .active(true)
                .email("jkowalski@nexway.pl")
                .roles(UserRole.BASIC.toString())
                .build();

        UserEntity m = UserEntity.builder()
                .id(UUID.randomUUID())
                .username("manager")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("mirek")
                .lastName("krawczyk")
                .active(true)
                .email("mkrawczyk@nexway.pl")
                .roles(Strings.join(Arrays.asList(UserRole.MANAGER.toString(), UserRole.BASIC.toString()), ';'))
                .build();

        UserEntity a = UserEntity.builder()
                .id(UUID.randomUUID())
                .username("admin")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("michał")
                .lastName("kruczek")
                .active(true)
                .email("mkruczek@nexway.pl")
                .roles(Strings.join(Arrays.asList(UserRole.ADMINISTRATOR.toString(),UserRole.MANAGER.toString(), UserRole.BASIC.toString()), ';'))
                .build();

        userRepository.save(b);
        userRepository.save(m);
        userRepository.save(a);
    }
}
