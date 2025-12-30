package com.example.mindbridge.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.Admin;
import com.example.mindbridge.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminProfileController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminProfileController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {

        Admin admin = userRepository
                .findAdminByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        model.addAttribute("admin", admin);
        model.addAttribute("activePage", "profile");
        return "admin-profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("admin") Admin formAdmin,
                                Principal principal,
                                RedirectAttributes redirectAttributes) {

        Admin admin = userRepository
                .findAdminByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setFullName(formAdmin.getFullName());
        admin.setEmail(formAdmin.getEmail());

        if (formAdmin.getPassword() != null && !formAdmin.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(formAdmin.getPassword()));
        }

        userRepository.save(admin);

        redirectAttributes.addFlashAttribute(
                "success", "Profile updated successfully!");

        return "redirect:/admin/profile";
    }
}
