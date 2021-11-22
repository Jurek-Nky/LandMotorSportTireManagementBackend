package com.dev.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User registerNewUser(@RequestParam(required = true, name = "vorName") String vorName,
                                @RequestParam(required = true, name = "nachName") String nachName,
                                @RequestParam(required = true, name = "rollenName") String rollenName) {
        return userService.addNewUser(vorName, nachName, rollenName);
    }
}
