package com.example.mindbridge.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CounselorDashboardController {

    @GetMapping("/counselor/dashboard")
    public String counselorDashboard(@AuthenticationPrincipal UserDetails user, Model model) {

        if (user == null) {
            return "redirect:/login";
        }

        // Dummy data for dashboard
        String counselorName = user.getUsername().equals("counselorAlice") ? "Alice Counselor" : "Counselor";
        int pendingRequests = 3;
        int todaysSessions = 2;
        int thisWeekSessions = 8;

        // Dummy appointments
        model.addAttribute("todaysAppointments", java.util.List.of(
                new DummyAppointment("John Student", "10:00 AM", "Voice Call", "Initial Consultation"),
                new DummyAppointment("Mary Johnson", "2:00 PM", "Video Call", "Follow-up")
        ));

        model.addAttribute("pendingAppointments", java.util.List.of(
                new DummyAppointment("David Lee", "2025-11-24 11:30 AM", "In-Person", "New session"),
                new DummyAppointment("Sarah Kim", "2025-11-25 1:00 PM", "Voice Call", "Check-in")
        ));

        model.addAttribute("counselorName", counselorName);
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("todaysSessions", todaysSessions);
        model.addAttribute("thisWeekSessions", thisWeekSessions);

        return "counselor-dashboard";
    }

    // Inner static class to represent dummy appointment
    public static class DummyAppointment {
        public String studentName;
        public String time;
        public String type;
        public String notes;

        public DummyAppointment(String studentName, String time, String type, String notes) {
            this.studentName = studentName;
            this.time = time;
            this.type = type;
            this.notes = notes;
        }

        public String getStudentName() { return studentName; }
        public String getTime() { return time; }
        public String getType() { return type; }
        public String getNotes() { return notes; }
    }
}
