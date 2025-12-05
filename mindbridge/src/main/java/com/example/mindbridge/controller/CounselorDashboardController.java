package com.example.mindbridge.controller;

import java.time.LocalDate;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.UserRepository;
import com.example.mindbridge.repository.AppointmentRepository;

@Controller
public class CounselorDashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/counselor/dashboard")
    public String counselorDashboard(@AuthenticationPrincipal UserDetails userDetails,
                                     Model model) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        // Get logged-in counselor
        User counselor = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (counselor == null) {
            return "redirect:/login";
        }

        // TODAY
        LocalDate today = LocalDate.now();

        // WEEK RANGE (Mon–Sun)
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);

        // Count Pending Requests
        int pendingCount = appointmentRepository.countByCounselorAndStatus(counselor, "PENDING");

        // Today's Approved Sessions
        int todaySessions = appointmentRepository
                .countByCounselorAndDateAndStatus(counselor, today, "APPROVED");

        // This Week Total Approved
        int weekTotal = appointmentRepository
                .countByCounselorAndDateBetweenAndStatus(counselor, weekStart, weekEnd, "APPROVED");

        // Get today's list
        var todayList = appointmentRepository
                .findByCounselorAndDateAndStatusOrderByTimeAsc(counselor, today, "APPROVED");

        // Add to model
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("todaySessions", todaySessions);
        model.addAttribute("weekTotal", weekTotal);
        model.addAttribute("todayList", todayList);
        model.addAttribute("counselorName", counselor.getFullName());

        return "counselor-dashboard";
    }
}
