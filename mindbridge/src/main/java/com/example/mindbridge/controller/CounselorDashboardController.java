package com.example.mindbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.UserRepository;

@Controller
public class CounselorDashboardController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/counselor/dashboard")
    public String counselorDashboard(@AuthenticationPrincipal UserDetails user, Model model) {

        if (user == null) {
            return "redirect:/login";
        }

        // Fetch the logged-in user from the database
        User u = userRepository.findByUsername(user.getUsername()).orElse(null);

        return "counselor-dashboard";
    }
}


