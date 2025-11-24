package com.example.mindbridge.controller;

import com.example.mindbridge.model.Student;
import com.example.mindbridge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private Student getLoggedInStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); 

        Optional<Student> optionalStudent = studentService.getStudentByUsername(username);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Logged-in student not found: " + username);
        }
        return optionalStudent.get();
    }

    @GetMapping("/student/dashboard")
    public String dashboard(Model model) {
        Student student = getLoggedInStudent();

        model.addAttribute("studentName", student.getFullName());

        return "student-dashboard"; 
    }

    @GetMapping("/student/profile")
    public String profile(Model model) {
        Student student = getLoggedInStudent();

        model.addAttribute("studentName", student.getFullName());
        model.addAttribute("student", student);

        return "profile"; 
    }

    @PostMapping("/student/profile")
    public String updateProfile(@ModelAttribute Student formStudent, Model model) {
        Student student = getLoggedInStudent();
        Student updatedStudent = studentService.updateStudentProfile(student.getId(), formStudent);

        model.addAttribute("studentName", updatedStudent.getFullName());
        model.addAttribute("student", updatedStudent);
        model.addAttribute("successMessage", "Profile updated successfully!");

        return "profile";
    }

    @PostMapping("student/profile/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) {
        Student student = getLoggedInStudent();

        try {
            studentService.changePassword(student.getId(), currentPassword, newPassword, confirmPassword);
            model.addAttribute("successMessage", "Password updated successfully!");
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }

        model.addAttribute("studentName", student.getFullName());
        model.addAttribute("student", student);

        return "profile";
    }
}
