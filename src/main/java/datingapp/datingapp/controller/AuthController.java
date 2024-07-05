package datingapp.datingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Ensure you have login.html in src/main/resources/templates
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Ensure you have home.html in src/main/resources/templates
    }
}
