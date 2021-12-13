package com.dev.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class ApplicationUserCotroller {
    private final ApplicationUserService applicationUserService;

    public ApplicationUserCotroller(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping
    public void createNewUser(@RequestBody ApplicationUser applicationUser){
        applicationUserService.createNewUser(applicationUser);
    }
}
