package com.dev.auth;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.dev.security.ApplicationUserRole.ADMIN;

@Configuration
public class ApplicationUserConfig implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserConfig(PasswordEncoder passwordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (applicationUserRepository.findByUsername("admin").isEmpty()) {
            ApplicationUser admin = new ApplicationUser("admin",
                    passwordEncoder.encode("12345678"),
                    ADMIN.getGrantedAuthorities(),
                    true, true, true, true);
            applicationUserRepository.save(admin);
        }
    }
}