package pl.kruczek.singlesignon.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kruczek.singlesignon.model.user.User;
import pl.kruczek.singlesignon.model.user.UserRepository;
import pl.kruczek.singlesignon.model.user.UserRole;

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

        User n = User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .username("noactive")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstname("agnieszka")
                .lastname("jurczyk")
                .score(0)
                .active(false)
                .email("ajurczyk@nexway.pl")
                .roles(UserRole.BASIC.name())
                .build();

        User b = User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .username("user")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstname("janusz")
                .lastname("kowalski")
                .score(80)
                .active(true)
                .email("jkowalski@nexway.pl")
                .roles(UserRole.BASIC.name())
                .build();

        User m = User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .username("manager")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstname("mirek")
                .lastname("krawczyk")
                .score(90)
                .active(true)
                .email("mkrawczyk@nexway.pl")
                .roles(Strings.join(Arrays.asList(UserRole.MANAGER.name(), UserRole.BASIC.name()), ';'))
                .build();

        User a = User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .username("admin")
                .password(passwordEncoder.encode("kochamkruczka"))
                .firstname("michał")
                .lastname("kruczek")
                .score(100)
                .active(true)
                .email("mkruczek@nexway.pl")
                .roles(Strings.join(Arrays.asList(UserRole.ADMINISTRATOR.name(),UserRole.MANAGER.name(), UserRole.BASIC.name()), ';'))
                .build();

        userRepository.save(n);
        userRepository.save(b);
        userRepository.save(m);
        userRepository.save(a);
    }
}
