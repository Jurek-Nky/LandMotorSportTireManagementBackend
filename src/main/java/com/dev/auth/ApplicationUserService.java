package com.dev.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder encoder;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository, PasswordEncoder encoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username %s not found", username)));
    }

    public void createNewUser(ApplicationUser preset) {
        if (checkPasswordValidity(preset)) {
            ApplicationUser user = new ApplicationUser(preset.getUsername(),
                    encoder.encode(preset.getPassword()),
                    preset.getAuthorities(),
                    preset.isAccountNonExpired(),
                    preset.isAccountNonLocked(),
                    preset.isCredentialsNonExpired(),
                    preset.isEnabled());
            applicationUserRepository.save(user);
        }
        else throw new IllegalStateException("password not secure");
    }

    private boolean checkPasswordValidity(ApplicationUser user) {
        return user.getPassword().length() >= 8;
    }
}
