package com.example.mindbridge.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.UserRepository;
import com.example.mindbridge.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/users")
    public String adminUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserModal(@PathVariable Long id, Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("showModal", true);
        return "admin-users";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUserForm(@PathVariable Long id,
                                @RequestParam("fullName") String fullName,
                                @RequestParam("email") String email,
                                Model model) {
        try {
            // Get the existing user to preserve password and role
            User existingUser = userService.getUserById(id);

            // Validation: Check for email uniqueness only if it has changed
            if (!existingUser.getEmail().equals(email)) {
                if (userRepository.existsByEmail(email)) {
                    model.addAttribute("error", "Email already exists: " + email);
                    return loadEditModal(id, model);
                }
            }

            // Update only the allowed fields (Full Name and Email only)
            existingUser.setFullName(fullName);
            existingUser.setEmail(email);
            // Username, department, and student ID remain unchanged

            // Save the updated user
            userService.updateUser(existingUser);
            return "redirect:/admin/users";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while updating the user. Please try again.");
            return loadEditModal(id, model);
        }
    }

    private String loadEditModal(Long id, Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("showModal", true);
        return "admin-users";
    }



    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
