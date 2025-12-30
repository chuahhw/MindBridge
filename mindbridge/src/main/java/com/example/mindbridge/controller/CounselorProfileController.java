package com.example.mindbridge.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.repository.UserRepository;

@Controller
@RequestMapping("/counselor")
public class CounselorProfileController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CounselorProfileController(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET: View counselor profile
    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {

        Counselor counselor = userRepository
                .findCounselorByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Counselor not found"));

        List<String> availabilityList = null;

        if (counselor.getAvailability() != null && !counselor.getAvailability().isBlank()) {
            // Only if database literally stores "EVERYDAY" populate all days
            if ("EVERYDAY".equals(counselor.getAvailability())) {
                availabilityList = Arrays.asList(
                        "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
                        "FRIDAY", "SATURDAY", "SUNDAY", "EVERYDAY"
                );
            } else {
                // Otherwise, split the stored value
                availabilityList = Arrays.asList(counselor.getAvailability().split(","));
            }
        }

        model.addAttribute("availabilityList", availabilityList);
        model.addAttribute("counselor", counselor);
        model.addAttribute("activePage", "profile");
        model.addAttribute("counselorName", counselor.getFullName());
        return "counselor-profile";
    }

    // POST: Update counselor profile
    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("fullName") String fullName,
                                @RequestParam("email") String email,
                                @RequestParam(value = "specialty", required = false) String specialty,
                                @RequestParam(value = "availability", required = false) String[] availability,
                                @RequestParam(value = "password", required = false) String password,
                                Principal principal,
                                RedirectAttributes redirectAttributes) {

        Counselor counselor = userRepository
                .findCounselorByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Counselor not found"));

        // Update editable fields
        counselor.setFullName(fullName);
        counselor.setEmail(email);
        counselor.setSpecialty(specialty);

        // Handle availability logic
        if (availability != null && availability.length > 0) {
            List<String> availabilityList = Arrays.asList(availability);
            String[] allDays = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};

            if (availabilityList.contains("EVERYDAY")) {
                counselor.setAvailability("EVERYDAY");
            } else if (availabilityList.containsAll(Arrays.asList(allDays))) {
                counselor.setAvailability("EVERYDAY");
            } else {
                counselor.setAvailability(String.join(",", availabilityList));
            }
        } else {
            counselor.setAvailability(null);
        }

        // Update password only if provided
        if (password != null && !password.isBlank()) {
            counselor.setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(counselor);

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/counselor/profile";
    }
}

