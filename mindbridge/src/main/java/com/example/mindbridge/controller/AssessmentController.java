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

import com.example.mindbridge.model.AssessmentResult;
import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.AssessmentAttempt;
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

    @GetMapping("/result/{attemptId}")
public String showAssessmentResult(@PathVariable Integer attemptId, Model model, Authentication authentication) {
    System.out.println("=== DEBUG: showAssessmentResult called ===");
    System.out.println("Attempt ID: " + attemptId);
    
    Student student = studentService.getLoggedInStudent(authentication);
    System.out.println("Student: " + student.getFullName() + " (ID: " + student.getId() + ")");
    
    model.addAttribute("studentName", student.getFullName());

    try {
        AssessmentAttempt attempt = assessmentService.getAssessmentAttemptById(attemptId);
        System.out.println("Found attempt: ID=" + attempt.getId());
        System.out.println("Attempt User ID: " + attempt.getUser().getId());
        System.out.println("Question Set: " + attempt.getQuestionSet().getName());
        System.out.println("Total Score: " + attempt.getTotalScore());
        System.out.println("Severity Level: " + attempt.getSeverityLevel());

        if (!attempt.getUser().getId().equals(student.getId())) {
            System.out.println("ERROR: Unauthorized access!");
            return "redirect:/student/assessments";
        }

        int maxScore = assessmentService.getMaxScoreForSet(attempt.getQuestionSet().getName());
        System.out.println("Max Score: " + maxScore);
        
        AssessmentResult result = new AssessmentResult(
                attempt.getTotalScore(),
                maxScore,
                attempt.getSeverityLevel());
        
        System.out.println("Created AssessmentResult: " + 
            "score=" + result.getScore() + 
            ", maxScore=" + result.getMaxScore() + 
            ", severity=" + result.getSeverity());
        
        model.addAttribute("result", result);
        model.addAttribute("assessmentName", attempt.getQuestionSet().getName());
        
        return "assessment-result";
        
    } catch (Exception e) {
        System.err.println("ERROR in showAssessmentResult: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", "Could not load assessment results: " + e.getMessage());
        return "redirect:/student/assessments";
    }
}}