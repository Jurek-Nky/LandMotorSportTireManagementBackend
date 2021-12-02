package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import com.dev.security.jwt.JwtTokenProvider;
import com.dev.security.principles.UserPrinciple;
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
        String jwt = tokenProvider.generateToken(authentication);
        return jwt;
    }

    @Transactional
    public User registerUser(User signUpRequest) {
        if(!this.isValidUserPass(signUpRequest)) return null;

        Optional<User> userInDb = userRepository.findUserByVorNameAndNachName(signUpRequest.vorName, signUpRequest.nachName);
        if (userInDb.isPresent()) {
            throw new IllegalStateException(String.format("User with vorname: %s and nachname: %s, already exists."));
        }
        // if combination of vorname and nachname is unique then add user
        User user = new User(signUpRequest.vorName, signUpRequest.nachName);

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole =  roleRepository.findRoleByRoleName(signUpRequest.roleName).get();
        user.setRole(userRole);

        User result = userRepository.save(user);

        return result;
    }

    public User getUserByMobileOrEmail(String vorName) {
        User user = userRepository.findUserByVorName(vorName).get();
        return user;
    }

    public UserPrinciple getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrinciple) authentication.getPrincipal();
    }

    public Long getCurrentUserId() {
        return this.getCurrentUser().getId();
    }

    public boolean isValidUserPass(User userModel) {
        if(userModel.getPassword().length() < 6 ) return false;
        return true;
    }
}
