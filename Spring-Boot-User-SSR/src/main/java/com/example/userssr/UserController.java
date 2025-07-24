package com.example.userssr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // server side rendered page
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users";
    }

    // form submission from SSR page
    @PostMapping("/users")
    public String addUserFromForm(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    // REST API
    @PostMapping("/api/users")
    @ResponseBody
    public User addUserApi(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getUsersApi() {
        return userRepository.findAll();
    }
}
