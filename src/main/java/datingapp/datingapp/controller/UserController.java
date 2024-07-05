package datingapp.datingapp.controller;

import datingapp.datingapp.entity.User;
import datingapp.datingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Optional<User> getUserProfile(@AuthenticationPrincipal OidcUser oidcUser) {
        String email = oidcUser.getEmail();
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
