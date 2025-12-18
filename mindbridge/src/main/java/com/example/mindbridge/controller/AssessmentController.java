package com.example.mindbridge.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.service.AssessmentService;
import com.example.mindbridge.service.StudentService;

@Controller
@RequestMapping("/student/assessments")
public class AssessmentController {

    private final StudentService studentService;
    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(StudentService studentService, AssessmentService assessmentService) {
        this.studentService = studentService;
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public String showAssessments(Model model, Authentication authentication) {
        Student student = studentService.getLoggedInStudent(authentication);
        model.addAttribute("studentName", student.getFullName());

        List<AssessmentAttempt> previousAttempts = assessmentService.getPreviousAttempts(student);
        model.addAttribute("previousAttempts", previousAttempts);

        return "self-assessments";
    }

    @GetMapping("/{setName}")
    public String takeAssessment(@PathVariable String setName, Model model, Authentication authentication) {
        Student student = studentService.getLoggedInStudent(authentication);
        model.addAttribute("studentName", student.getFullName());

        QuestionSet questionSet = assessmentService.getQuestionSetByName(setName);
        model.addAttribute("questionSet", questionSet);

        return "take-assessment";
    }

    @PostMapping("/{setName}")
    public String submitAssessment(@PathVariable String setName, @RequestParam Map<String, String> answers,
            Authentication authentication, RedirectAttributes redirectAttributes) {
        Student student = studentService.getLoggedInStudent(authentication);
        AssessmentAttempt attempt = assessmentService.saveAssessment(student, setName, answers);

        redirectAttributes.addFlashAttribute("assessmentAttemptId", attempt.getId());
        return "redirect:/student/assessments/result/" + attempt.getId();
    }

    // FIX: Change this to just "/result/{attemptId}" since the controller already has "/student/assessments"
    @GetMapping("/result/{attemptId}")
public String viewResult(@PathVariable Integer attemptId, Model model, Authentication authentication) {
    try {
        System.out.println("=== DEBUG: viewResult called ===");
        System.out.println("Attempt ID: " + attemptId);
        
        // Get the current student to verify ownership
        Student student = studentService.getLoggedInStudent(authentication);
        System.out.println("Student: " + student.getFullName() + " (ID: " + student.getId() + ")");
        
        // Add student name to model - THIS IS THE FIX!
        model.addAttribute("studentName", student.getFullName());
        
        // Get the assessment attempt
        AssessmentAttempt attempt = assessmentService.getAssessmentAttemptById(attemptId);
        System.out.println("Found attempt: ID=" + attempt.getId());
        System.out.println("Attempt User ID: " + attempt.getUser().getId());
        
        // Verify that this attempt belongs to the current student
        if (!attempt.getUser().getId().equals(student.getId())) {
            System.err.println("ERROR: Unauthorized access attempt!");
            throw new RuntimeException("Unauthorized access to assessment result");
        }
        
        // Calculate max score and percentage
        String setName = attempt.getQuestionSet().getName();
        int maxScore = assessmentService.getMaxScoreForSet(setName);
        double percentage = (attempt.getTotalScore() * 100.0) / maxScore;
        
        System.out.println("Question Set: " + setName);
        System.out.println("Total Score: " + attempt.getTotalScore());
        System.out.println("Severity Level: " + attempt.getSeverityLevel());
        System.out.println("Max Score: " + maxScore);
        System.out.println("Percentage: " + percentage);
        
        // Add attributes to model
        model.addAttribute("attempt", attempt);
        model.addAttribute("assessmentName", setName);
        model.addAttribute("maxScore", maxScore);
        model.addAttribute("percentage", Math.round(percentage));
        
        // For backward compatibility, also add 'result'
        model.addAttribute("result", attempt);
        
        // Return the correct template - make sure this template exists
        return "assessment-result";
        
    } catch (Exception e) {
        System.err.println("ERROR in viewResult: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", "Failed to load assessment result: " + e.getMessage());
        return "error";
    }
}
}