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
            Role adminRole;
            if (!roleRepository.existsByRoleName("Admin")) {
                adminRole = new Role();
                adminRole.setRoleName("Admin");
                adminRole = roleRepository.save(adminRole);
            }
            else {
                adminRole = roleRepository.findRoleByRoleName("Admin").get();
            }
            if (!roleRepository.existsByRoleName("Manager")) {
                Role managerRole = new Role();
                managerRole.setRoleName("Manager");
                roleRepository.save(managerRole);
            }
            if (!roleRepository.existsByRoleName("Ingenieur")) {
                Role ingRole = new Role();
                ingRole.setRoleName("Ingenieur");
                roleRepository.save(ingRole);
            }
            if (!roleRepository.existsByRoleName("Employee")) {
                Role employeeRole = new Role();
                employeeRole.setRoleName("Employee");
                roleRepository.save(employeeRole);
            }
            if (!userRepository.existsUserByRole_RoleName("Admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("12345678");
                admin.setRole(adminRole);
                authService.registerUser(admin);
            }
        };
    }

}
