package com.dev.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;


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

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        String jwt = authService.getJwt(loginRequest);
        return ResponseEntity.ok(jwt);
    }
}
