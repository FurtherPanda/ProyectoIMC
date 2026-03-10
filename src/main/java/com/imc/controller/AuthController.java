package com.imc.controller;

import com.imc.model.User;
import com.imc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        return "dashboard";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {

        userService.registerUser(user);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        try {

            User user = userService.login(username, password);

            session.setAttribute("user", user);

            return "redirect:/dashboard";

        } catch (Exception e) {

            model.addAttribute("error", "Invalid username or password");

            return "login";
        }
    }
}