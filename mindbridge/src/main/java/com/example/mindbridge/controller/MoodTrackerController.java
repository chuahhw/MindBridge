package com.example.mindbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.Student;
import com.example.mindbridge.service.MoodService;
import com.example.mindbridge.service.StudentService;

@Controller
public class MoodTrackerController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MoodService moodService;

    @GetMapping("/mood-tracker")
    public String moodTracker(@AuthenticationPrincipal UserDetails user, Model model) {

        if (user == null) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByUsername(user.getUsername())
                .orElse(null);

        if (student == null) {
            model.addAttribute("studentName", "Student");
            model.addAttribute("moods", null);
            model.addAttribute("streak", 0);
            model.addAttribute("average7Days", 0);
            model.addAttribute("totalEntries", 0);
            return "mood-tracker";
        }

        model.addAttribute("student", student);
        model.addAttribute("studentName", student.getFullName());
        model.addAttribute("moods", moodService.getMoodsForStudent(student));
        model.addAttribute("latestMood", moodService.getLatestMood(student));
        model.addAttribute("streak", moodService.getCurrentStreak(student));
        model.addAttribute("average7Days", moodService.get7DayAverage(student));
        model.addAttribute("totalEntries", moodService.getTotalEntries(student));

        return "mood-tracker";
    }

    @PostMapping("/mood/save")
    public String addMood(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(value = "moodValue", required = false) Integer moodValue,
            @RequestParam(value = "note", required = false) String note) {

        if (user == null) {
            return "redirect:/login";
        }

        if (moodValue == null) {
            // No mood selected; redirect back with optional error message
            return "redirect:/mood-tracker?error=selectMood";
        }

        studentService.getStudentByUsername(user.getUsername()).ifPresent(student -> {
            try {
                // Convert numeric mood to label
                String moodLabel = convertMoodValue(moodValue);

                // Add mood entry
                moodService.addMood(student, moodLabel, (note != null ? note : ""));
            } catch (Exception e) {
                // Log exception (optional)
                System.err.println("Error saving mood: " + e.getMessage());
            }
        });

        return "redirect:/mood-tracker";
    }

    private String convertMoodValue(int value) {
        return switch (value) {
            case 5 -> "Great";
            case 4 -> "Good";
            case 3 -> "Okay";
            case 2 -> "Not Great";
            case 1 -> "Bad";
            case 0 -> "Awful";
            default -> "Unknown";
        };
    }
}
