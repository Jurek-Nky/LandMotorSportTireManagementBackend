package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userConf(AuthService authService, UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (!userRepository.existsUserByRole_RoleName("Admin")) {
                User admin = new User();
                admin.setVorName("admin");
                admin.setNachName("admin");
                admin.setPassword(rndString(20));
                System.out.println(String.format("##############################\n" +
                        "admin pw : %s\n" +
                        "##############################", admin.getPassword()));
                Role adminRole = new Role();
                adminRole.setRoleName("Admin");
                adminRole = roleRepository.save(adminRole);

                admin.setRole(adminRole);

                authService.registerUser(admin);
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
