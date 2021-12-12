package com.dev.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) throws JsonProcessingException {
        String jwt = authService.getJwt(loginRequest);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping(path = "/role")
    public String getRole(@RequestParam(name = "name") String name,
                          @RequestParam(name = "surname") String surname) {
        return authService.getRole(name, surname);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUserById(@PathVariable(name = "id") Long userid) {
        userService.deleteUserById(userid);
    }

    @PutMapping("/resetpw")
    public User resetPassword(@RequestParam(name = "vorname") String vorName,
                              @RequestParam(name = "nachname") String nachName,
                              @RequestParam(name = "pwold") String pwOld,
                              @RequestParam(name = "pwnew") String pwNew) {
        return authService.resetUserPassword(vorName, nachName, pwOld, pwNew);
    }

    @PutMapping("/admin/resetpw")
    public User resetPaswordWithAdmin(@RequestParam(name = "id") Long userid,
                                      @RequestParam(name = "pwnew") String pwNew) {
        return authService.adminResetUserPassword(userid, pwNew);
    }
}
