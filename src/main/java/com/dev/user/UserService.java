package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("No users were found.");
        }
        return users;
    }

    public User addNewUser(String vorName, String nachName, String rollenName) {
        // checking if the user already exists
        Optional<User> userInDb = userRepository.findUserByVorNameAndNachName(vorName, nachName);
        if (userInDb.isPresent()) {
            throw new IllegalStateException(String.format("User with vorname: %s and nachname: %s, already exists.", vorName, nachName));
        }
        // if combination of vorname and nachname is unique then add user
        User user = new User(vorName, nachName);
        Optional<Role> rolle = roleRepository.findRoleByRoleName(rollenName);
        if (rolle.isEmpty()) {
            rolle = Optional.of(new Role(rollenName));
        }
        user.setRolle(rolle.get());
        userRepository.save(user);
        return user;
    }
}
