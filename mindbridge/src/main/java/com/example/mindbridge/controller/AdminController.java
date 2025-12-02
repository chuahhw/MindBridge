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

import com.example.mindbridge.model.Admin;
import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.UserRepository;
import com.example.mindbridge.service.AdminDashboardService;
import com.example.mindbridge.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AdminDashboardService adminDashboardService;

    public AdminController(UserService userService, UserRepository userRepository, AdminDashboardService adminDashboardService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        var recentActivities = adminDashboardService.getRecentActivities(10);
        model.addAttribute("recentActivities", recentActivities);
        return "admin-dashboard";
    }

    @GetMapping("/users")
    public String adminUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @GetMapping("/users/new")
    public String createUserModal(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("showCreateModal", true);
        return "admin-users";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam("fullName") String fullName,
                            @RequestParam("username") String username,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password,
                            @RequestParam("confirmPassword") String confirmPassword,
                            @RequestParam("role") String role,
                            @RequestParam(value = "studentId", required = false) String studentId,
                            @RequestParam(value = "department", required = false) String department,
                            @RequestParam(value = "specialty", required = false) String specialty,
                            @RequestParam(value = "availability", required = false) String availability,
                            Model model) {
        try {
            // Validation
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Passwords do not match");
                return loadCreateModal(model);
            }

            User newUser;
            switch (role.toUpperCase()) {
                case "STUDENT":
                    newUser = new Student(fullName, username, email, studentId, department, password, "STUDENT");
                    break;
                case "COUNSELOR":
                    newUser = new Counselor(fullName, username, email, password, specialty, availability);
                    break;
                case "ADMIN":
                    newUser = new Admin(fullName, username, email, password);
                    break;
                default:
                    model.addAttribute("error", "Invalid user role selected");
                    return loadCreateModal(model);
            }

            userService.registerUser(newUser);
            return "redirect:/admin/users";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while creating the user: " + e.getMessage());
            return loadCreateModal(model);
        }
    }

    private String loadCreateModal(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("showCreateModal", true);
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
