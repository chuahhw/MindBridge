package com.example.mindbridge.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.User;
import com.example.mindbridge.service.AppointmentService;
import com.example.mindbridge.service.UserService;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    // Student: View appointments
    // Student: View appointments
    @GetMapping("/student/appointments")
    public String getStudentAppointments(Authentication authentication, Model model) {
        User student = userService.getLoggedInUser(authentication);
        List<Appointment> appointments = appointmentService.getStudentAppointments(student.getId());
        List<User> counselors = userService.getAllCounselors();
        
        model.addAttribute("appointments", appointments);
        model.addAttribute("counselors", counselors);
        model.addAttribute("studentName", student.getFullName()); // ADD THIS LINE
        model.addAttribute("activePage", "appointments");
        
        return "student-appointments"; // or "appointments" - depending on your file name
    }

    // Student: Book new appointment
    @PostMapping("/student/appointments")
    public String bookAppointment(
            @RequestParam Long counselorId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime time,
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "") String notes,  // ← ADD THIS
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        
        System.out.println("📝 Booking appointment...");
        System.out.println("Counselor ID: " + counselorId);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Type: " + type);
        System.out.println("Notes: " + notes);
        
        try {
            User student = userService.getLoggedInUser(authentication);
            User counselor = userService.getUserById(counselorId);
            
            if (!appointmentService.isTimeSlotAvailable(counselor, date, time)) {
                redirectAttributes.addFlashAttribute("error", "Selected time slot is not available");
                return "redirect:/student/appointments";
            }
            
            appointmentService.createAppointment(student, counselor, date, time, type, notes);
            redirectAttributes.addFlashAttribute("success", "Appointment booked successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();  // ← ADD THIS to see errors in console
            redirectAttributes.addFlashAttribute("error", "Failed to book appointment: " + e.getMessage());
        }
        
        return "redirect:/student/appointments";
    }

    // Counselor: View all appointments
    @GetMapping("/counselor/appointments")
    public String getCounselorAppointments(Authentication authentication, Model model) {
        User counselor = userService.getLoggedInUser(authentication);
        List<Appointment> allAppointments = appointmentService.getCounselorAppointments(counselor.getId());
        List<Appointment> pendingAppointments = appointmentService.getPendingAppointments(counselor.getId());
        
        model.addAttribute("appointments", allAppointments);
        model.addAttribute("pendingAppointments", pendingAppointments);
        model.addAttribute("counselorName", counselor.getFullName()); // ADD THIS
        model.addAttribute("activePage", "appointments");
        return "counselor-appointments";
    }

    // Counselor: Update appointment status
    @PostMapping("/counselor/appointments/{id}/status")
    public String updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam String status,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        
        try {
            appointmentService.updateAppointmentStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Appointment status updated!");
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update appointment status");
        }
        
        return "redirect:/counselor/appointments";
    }
}