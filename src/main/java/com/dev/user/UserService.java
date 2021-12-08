package com.dev.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("No users were found.");
        }
        return users;
    }

    public void deleteUserById(Long userid){
        if (userRepository.findById(userid).isEmpty()){
            throw new IllegalStateException(String.format("No user with ID %s was found.",userid));
        }
        userRepository.deleteById(userid);
    }
}
