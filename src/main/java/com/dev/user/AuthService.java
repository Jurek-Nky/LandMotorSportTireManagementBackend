package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import com.dev.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    public String getJwt(User userModel) {
        String cridential = userModel.vorName;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        cridential,
                        userModel.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Transactional
    public User registerUser(User signUpRequest) {
        if (!this.isValidUserPass(signUpRequest))
            throw new IllegalStateException("Password must be at least 8 characters.");

        Optional<User> userInDb = userRepository.findUserByVorNameAndNachName(signUpRequest.vorName, signUpRequest.nachName);
        if (userInDb.isPresent()) {
            throw new IllegalStateException(
                    String.format("User with vorname: %s and nachname: %s, already exists.",
                            signUpRequest.vorName,
                            signUpRequest.nachName)
            );
        }
        // if combination of vorname and nachname is unique then add user
        User user = new User(signUpRequest.vorName, signUpRequest.nachName);

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Optional<Role> userRole = roleRepository.findRoleByRoleName(signUpRequest.roleName);
        if (userRole.isEmpty()) {
            throw new IllegalStateException(String.format("No role found with name %s .", signUpRequest.roleName));
        }
        user.setRole(userRole.get());

        return userRepository.save(user);
    }

    public boolean isValidUserPass(User userModel) {
        return userModel.getPassword().length() >= 8;
    }
}
