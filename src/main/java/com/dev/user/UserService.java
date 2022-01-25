package com.dev.user;

import com.dev.role.Role;
import com.dev.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteUserById(Long userid) {
        if (userRepository.findById(userid).isEmpty()) {
            throw new IllegalStateException(String.format("No user with ID %s was found.", userid));
        }
        userRepository.deleteById(userid);
    }

    @Transactional
    public User changeUserRole(Long userId, String newRole) {
        Optional<Role> role = roleRepository.findRoleByRoleName(newRole);
        if (role.isEmpty()) {
            throw new IllegalStateException(String.format("Role with name $s could not be found.", role));
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalStateException(String.format("No User with ID $s was found.", userId));
        }
        user.get().setRole(role.get());
        return user.get();
    }
}
