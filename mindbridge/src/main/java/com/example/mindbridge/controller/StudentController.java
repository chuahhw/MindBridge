package com.example.mindbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.Student;
import com.example.mindbridge.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private Student getLoggedInStudent() {
        return studentService.getLoggedInStudent(null); // Service will get auth from context
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Student student = getLoggedInStudent();

        model.addAttribute("studentName", student.getFullName());

        return "student-dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Student student = getLoggedInStudent();

        model.addAttribute("studentName", student.getFullName());
        model.addAttribute("student", student);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Student formStudent, Model model) {
        Student student = getLoggedInStudent();
        Student updatedStudent = studentService.updateStudentProfile(student.getId(), formStudent);

        model.addAttribute("studentName", updatedStudent.getFullName());
        model.addAttribute("student", updatedStudent);
        model.addAttribute("successMessage", "Profile updated successfully!");

        return "profile";
    }

    @PostMapping("/profile/change-password")
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
