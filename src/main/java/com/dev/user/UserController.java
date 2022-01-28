package com.dev.user;

import com.dev.role.Role;
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
    public Role getRole(@RequestParam(name = "u") String username) {
        return authService.getRole(username);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUserById(@PathVariable(name = "id") Long userid) {
        userService.deleteUserById(userid);
    }

    @PutMapping("/resetpw")
    public User resetPassword(@RequestParam(name = "username") String username,
                              @RequestParam(name = "pwold") String pwOld,
                              @RequestParam(name = "pwnew") String pwNew) {
        return authService.resetUserPassword(username, pwOld, pwNew);
    }

    @PutMapping("/update/userrole")
    public User changeUserRole(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "role") String newRole) {
        return userService.changeUserRole(id, newRole);
    }

    @PutMapping("/admin/resetpw")
    public User resetPaswordWithAdmin(@RequestParam(name = "id") Long userid,
                                      @RequestParam(name = "pwnew") String pwNew) {
        return authService.adminResetUserPassword(userid, pwNew);
    }

    @PutMapping("/update/{id}/userSettings")
    public UserSettings updateUserSettings(@PathVariable(name = "id") Long userID,
                                   @RequestBody() UserSettings userSettings) {
        return userService.updateUserSettings(userID, userSettings);
    }

    @GetMapping("/userSettings/{id}")
    public UserSettings getUserSettings(@PathVariable(name = "id" )Long userID){
        return userService.getUserSettingsByID(userID);
    }
}
