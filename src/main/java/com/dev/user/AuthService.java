package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import com.dev.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
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


    public String getJwt(User userModel) throws JsonProcessingException {
        String cridential = userModel.getFirstName();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        cridential,
                        userModel.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(tokenProvider.generateToken(authentication));
    }

    @Transactional
    public User registerUser(User signUpRequest) {
        if (!this.isValidUserPass(signUpRequest.getPassword()))
            throw new IllegalStateException("Password must be at least 8 characters.");

        Optional<User> userInDb = userRepository.findUserByFirstNameAndLastName(signUpRequest.firstName, signUpRequest.lastName);
        if (userInDb.isPresent()) {
            throw new IllegalStateException(
                    String.format("User with vorname: %s and nachname: %s, already exists.",
                            signUpRequest.firstName,
                            signUpRequest.lastName)
            );
        }
        // if combination of vorname and nachname is unique then add user
        User user = new User(signUpRequest.firstName, signUpRequest.lastName);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Optional<Role> userRole = roleRepository.findRoleByRoleName(signUpRequest.getRole().getRoleName());
        if (userRole.isEmpty()) {
            throw new IllegalStateException(String.format("No role found with name %s .", signUpRequest.getRole().getRoleName()));
        }
        user.setRole(userRole.get());

        return userRepository.save(user);
    }

    @Transactional
    public User resetUserPassword(String vorname, String nachname, String oldPassword, String newPassword) {
        Optional<User> user = userRepository.findUserByFirstNameAndLastName(vorname, nachname);
        if (user.isEmpty()) {
            throw new IllegalStateException(String.format("No user with name %s and surname %s was found.", vorname, nachname));
        } else if (!passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            throw new IllegalStateException("Wrong password.");
        } else if (!isValidUserPass(newPassword)) {
            throw new IllegalStateException("Password must be at least  8 characters long.");
        }
        user.get().setPassword(passwordEncoder.encode(newPassword));

        return user.get();
    }

    @Transactional
    public User adminResetUserPassword(Long userid, String newPassword) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isEmpty()) {
            throw new IllegalStateException(String.format("No user with ID %s was found", userid));
        }
        user.get().setPassword(passwordEncoder.encode(newPassword));
        return user.get();

    }

    public boolean isValidUserPass(String password) {
        return password.length() >= 8;
    }

    public String getRole(String name, String surname) {
        Optional<User> user = userRepository.findUserByFirstNameAndLastName(name, surname);
        if (user.isEmpty()) {
            throw new IllegalStateException("No user found with that name.");
        }
        return user.get().getRole().getRoleName();
    }
}
