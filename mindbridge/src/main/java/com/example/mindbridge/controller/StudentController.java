package com.example.mindbridge.controller; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.StudentProgress;
import com.example.mindbridge.service.AppointmentService;
import com.example.mindbridge.service.LearningModuleService;
import com.example.mindbridge.service.MoodService;
import com.example.mindbridge.service.StudentService;
import com.example.mindbridge.service.UserService; 

@Controller 
@RequestMapping("/student") 
public class StudentController { 
    private final StudentService studentService; 
    private final LearningModuleService learningModuleService;
    
    @Autowired
    private MoodService moodService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired 
    public StudentController(StudentService studentService, LearningModuleService learningModuleService) { 
        this.studentService = studentService; 
        this.learningModuleService = learningModuleService;
    } 
    
    private Student getLoggedInStudent() { 
        return studentService.getLoggedInStudent(null); 
    } 

    @GetMapping("/dashboard") 
    public String dashboard(@AuthenticationPrincipal UserDetails user, Authentication authentication, Model model,
            RedirectAttributes redirectAttributes) { 

        if (user == null) {
            return "redirect:/login";
        }

        Student student = studentService.getLoggedInStudent(authentication);
        if (student == null) {
            model.addAttribute("streak", 0);
            return "student-dashboard";
        }

        List<LearningModule> modules = learningModuleService.getAllModules();
        List<StudentProgress> progress = learningModuleService.getStudentProgress(student);
        List<Appointment> appointments = appointmentService.getStudentAppointments(student.getId());
        Map<Long, Boolean> progressMap = new HashMap<>();
        for (StudentProgress p : progress) {
            if (p.getModule() != null) {
                progressMap.put(p.getModule().getId(), p.getCompleted() != null && p.getCompleted());
            }
        }
        long completedCount = learningModuleService.getCompletedCount(student);
        long totalCount = learningModuleService.getTotalCount();

        long percentage = 0;
        if (totalCount > 0) {
            percentage = (completedCount * 100) / totalCount;
        }

        // Find the nearest upcoming appointment
        Appointment nextAppointment = null;

        if (appointments != null && !appointments.isEmpty()) {

            nextAppointment = appointments.stream()
                .filter(a -> "APPROVED".equalsIgnoreCase(a.getStatus())) // only approved
                .filter(a -> a.getDate() != null 
                        && !a.getDate().isBefore(java.time.LocalDate.now())) // not past date
                .sorted((a1, a2) -> {
                    int dateCompare = a1.getDate().compareTo(a2.getDate());
                    if (dateCompare != 0) return dateCompare;
                    return a1.getTime().compareTo(a2.getTime());
                })
                .findFirst()
                .orElse(null);
        }

        // Add to model
        model.addAttribute("appointment", nextAppointment);
        model.addAttribute("appointments", appointments);
        model.addAttribute("studentName", student.getFullName()); 
        model.addAttribute("modules", modules); 
        model.addAttribute("progressMap", progressMap);
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("percentage", percentage); 
        model.addAttribute("streak", moodService.getCurrentStreak(student));
        return "student-dashboard"; 
    } 

    
    @GetMapping("/profile") 
    public String profile(Model model) { 
        Student student = getLoggedInStudent(); 
        model.addAttribute("studentName", student.getFullName()); 
        model.addAttribute("student", student); 
        // Add milestone data
        long completedCount = learningModuleService.getCompletedCount(student);
        long totalCount = learningModuleService.getTotalCount();
        long percentage = 0;
        if (totalCount > 0) {
            percentage = (completedCount * 100) / totalCount;
        }
        String level;
        if (percentage >= 75) level = "Advanced";
        else if (percentage >= 40) level = "Intermediate";
        else if (percentage > 0) level = "Beginner";
        else level = "New Learner";

        model.addAttribute("completedCount", completedCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("percentage", percentage);
        model.addAttribute("level", level);

        return "profile"; 
    } 
    
    @PostMapping("/profile") 
    public String updateProfile(@ModelAttribute Student formStudent, Model model) { 
        Student student = getLoggedInStudent(); 
        Student updatedStudent = studentService.updateStudentProfile(student.getId(), formStudent); 
        model.addAttribute("studentName", updatedStudent.getFullName()); 
        model.addAttribute("student", updatedStudent); 
        model.addAttribute("successMessage", "Profile updated successfully!"); 
        // ensure milestone attributes present after update
        long completedCount = learningModuleService.getCompletedCount(updatedStudent);
        long totalCount = learningModuleService.getTotalCount();
        long percentage = 0;
        if (totalCount > 0) {
            percentage = (completedCount * 100) / totalCount;
        }
        String level;
        if (percentage >= 75) level = "Advanced";
        else if (percentage >= 40) level = "Intermediate";
        else if (percentage > 0) level = "Beginner";
        else level = "New Learner";
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("percentage", percentage);
        model.addAttribute("level", level);

        return "profile"; 
    } 
    
    @PostMapping("/profile/change-password") 
    public String changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) { 
        Student student = getLoggedInStudent(); 
        try { 
            studentService.changePassword(student.getId(), currentPassword, newPassword, confirmPassword); 
            model.addAttribute("successMessage", "Password updated successfully!"); 
        } catch (IllegalArgumentException ex) { 
            model.addAttribute("errorMessage", ex.getMessage()); 
        } 
        model.addAttribute("studentName", student.getFullName()); 
        model.addAttribute("student", student);
        // ensure milestone attributes present after change password
        long completedCount = learningModuleService.getCompletedCount(student);
        long totalCount = learningModuleService.getTotalCount();
        long percentage = 0;
        if (totalCount > 0) {
            percentage = (completedCount * 100) / totalCount;
        }
        String level;
        if (percentage >= 75) level = "Advanced";
        else if (percentage >= 40) level = "Intermediate";
        else if (percentage > 0) level = "Beginner";
        else level = "New Learner";
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("percentage", percentage);
        model.addAttribute("level", level);
        return "profile"; 
    } 
}