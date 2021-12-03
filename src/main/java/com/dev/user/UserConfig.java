package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Scanner;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userConf(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsUserByRole_RoleName("admin")) {
                User admin = new User();
                admin.setVorName("admin");
                admin.setNachName("admin");
                String pw = rndString(20);
                System.out.println(String.format("################################################\n" +
                        "Admin password = %s" +
                        "\n################################################", pw));
                admin.setPassword(passwordEncoder.encode(pw));

                Optional<Role> role = roleRepository.findRoleByRoleName("Admin");
                role.ifPresent(admin::setRole);
                if (role.isEmpty()) {
                    Role temp = new Role();
                    temp.setRoleName("Admin");
                    role = Optional.of(roleRepository.save(temp));
                    admin.setRole(role.get());
                }
                userRepository.save(admin);
            }

        };


    }

    static String rndString(int n) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (chars.length() * Math.random());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
