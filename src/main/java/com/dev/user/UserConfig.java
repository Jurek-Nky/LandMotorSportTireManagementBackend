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
                System.out.printf(
                        "##############################\nadmin pw : %s\n##############################%n",
                        admin.getPassword());

                Role adminRole = new Role();
                adminRole.setRoleName("Admin");
                adminRole = roleRepository.save(adminRole);

                Role managerRole = new Role();
                managerRole.setRoleName("Manager");
                roleRepository.save(managerRole);

                Role ingRole = new Role();
                ingRole.setRoleName("Ingenieur");
                roleRepository.save(ingRole);

                Role employeeRole = new Role();
                employeeRole.setRoleName("Employee");
                roleRepository.save(employeeRole);

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
