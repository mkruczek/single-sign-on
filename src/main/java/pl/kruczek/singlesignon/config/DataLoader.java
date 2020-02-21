package pl.kruczek.singlesignon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kruczek.singlesignon.model.UserEntity;
import pl.kruczek.singlesignon.model.UserRepository;
import pl.kruczek.singlesignon.model.UserRole;

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
                .login("basic")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("jausz")
                .lastName("kowalski")
                .email("jkowalski@nexway.pl")
                .role(UserRole.BASIC)
                .build();

        UserEntity m = UserEntity.builder()
                .id(UUID.randomUUID())
                .login("manager")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("mirek")
                .lastName("krawczyk")
                .email("mkrawczyk@nexway.pl")
                .role(UserRole.MANAGER)
                .build();

        UserEntity a = UserEntity.builder()
                .id(UUID.randomUUID())
                .login("admin")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstName("michał")
                .lastName("kruczek")
                .email("mkrawczyk@nexway.pl")
                .role(UserRole.ADMINISTRATOR)
                .build();

        userRepository.save(b);
        userRepository.save(m);
        userRepository.save(a);
    }
}
