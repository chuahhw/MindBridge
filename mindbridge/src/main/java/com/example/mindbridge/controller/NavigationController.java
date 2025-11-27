package com.example.mindbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/student/home")
    public String redirectStudentHome() {
        return "redirect:/student/dashboard";
    }

}
