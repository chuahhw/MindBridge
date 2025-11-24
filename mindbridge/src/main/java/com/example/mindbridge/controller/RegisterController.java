package com.example.mindbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.Admin;
import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm() {
        return "register"; 
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String fullName,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String department,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String role,   
            Model model
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        if (userRepository.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user;

        if ("STUDENT".equalsIgnoreCase(role)) {
            user = new Student(fullName, username, email, studentId, department, encodedPassword, "STUDENT");
        } else if ("COUNSELOR".equalsIgnoreCase(role)) {
            user = new Counselor(fullName, username, email, encodedPassword);
        } else if ("ADMIN".equalsIgnoreCase(role)) {
            user = new Admin(fullName, username, email, encodedPassword);
        } else {
            model.addAttribute("error", "Invalid role selected!");
            return "register";
        }

        userRepository.save(user);

        model.addAttribute("success", "Account created successfully!");
        return "register";
    }
}
