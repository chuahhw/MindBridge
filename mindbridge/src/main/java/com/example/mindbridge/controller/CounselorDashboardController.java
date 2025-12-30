package com.example.mindbridge.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.AppointmentRepository;
import com.example.mindbridge.repository.UserRepository;

@Controller
public class CounselorDashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // ----------------------------------------------------
    //           COUNSELOR DASHBOARD VIEW
    // ----------------------------------------------------
    @GetMapping("/counselor/dashboard")
    public String counselorDashboard(@AuthenticationPrincipal UserDetails userDetails,
                                     Model model) {

        if (userDetails == null) return "redirect:/login";

        // Fetch logged-in user
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null || !(user instanceof Counselor)) return "redirect:/login";

        Counselor counselor = (Counselor) user;

        // -----------------------------
        //     APPOINTMENTS + METRICS
        // -----------------------------
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);

        int pendingCount = appointmentRepository.countByCounselorAndStatus(counselor, "PENDING");
        int todaySessions = appointmentRepository.countByCounselorAndDateAndStatus(counselor, today, "APPROVED");
        int weekTotal = appointmentRepository.countByCounselorAndDateBetweenAndStatus(counselor, weekStart, weekEnd, "APPROVED");

        var todayList = appointmentRepository.findByCounselorAndDateAndStatusOrderByTimeAsc(counselor, today, "APPROVED");

        // -----------------------------
        //     SEND DATA TO FRONTEND
        // -----------------------------
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("todaySessions", todaySessions);
        model.addAttribute("weekTotal", weekTotal);
        model.addAttribute("todayList", todayList);

        model.addAttribute("counselorName", counselor.getFullName());

        return "counselor-dashboard";
    }
}
