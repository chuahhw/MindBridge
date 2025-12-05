package com.example.mindbridge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.model.QuizQuestion;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.StudentProgress;
import com.example.mindbridge.model.QuizAttempt;
import com.example.mindbridge.service.LearningModuleService;
import com.example.mindbridge.service.QuizService;
import com.example.mindbridge.service.StudentService;

@Controller
@RequestMapping("/student")
public class LearningController {

    private static final Logger logger = LoggerFactory.getLogger(LearningController.class);

    private final LearningModuleService learningModuleService;
    private final StudentService studentService;
    private final QuizService quizService;

    @Autowired
    public LearningController(LearningModuleService learningModuleService, StudentService studentService,
            QuizService quizService) {
        this.learningModuleService = learningModuleService;
        this.studentService = studentService;
        this.quizService = quizService;
    }

    // Main learning page - shows all modules
    @GetMapping("/learning")
    public String getLearningModules(Authentication authentication, Model model,
            RedirectAttributes redirectAttributes) {
        logger.info("=== LEARNING ENDPOINT CALLED ===");

        try {
            logger.info("Step 1: Getting logged-in student...");
            Student student = studentService.getLoggedInStudent(authentication);
            logger.info("Step 1 SUCCESS: Student found - '{}'", student.getUsername());

            logger.info("Step 2: Getting all learning modules...");
            List<LearningModule> modules = learningModuleService.getAllModules();
            logger.info("Step 2 SUCCESS: Found {} modules.", modules.size());

            logger.info("Step 3: Getting student progress records...");
            List<StudentProgress> progress = learningModuleService.getStudentProgress(student);
            logger.info("Step 3 SUCCESS: Found {} progress records.", progress.size());

            logger.info("Step 4: Creating progress map...");
            Map<Long, Boolean> progressMap = new HashMap<>();
            for (StudentProgress p : progress) {
                if (p.getModule() != null) {
                    progressMap.put(p.getModule().getId(), p.getCompleted() != null && p.getCompleted());
                }
            }
            logger.info("Step 4 SUCCESS: Progress map created with {} entries.", progressMap.size());

            logger.info("Step 5: Getting completed and total module counts...");
            long completedCount = learningModuleService.getCompletedCount(student);
            long totalCount = learningModuleService.getTotalCount();
            logger.info("Step 5 SUCCESS: Counts are {}/{}", completedCount, totalCount);

            logger.info("Step 6: Adding attributes to the model...");
            model.addAttribute("studentName", student.getFullName());
            model.addAttribute("modules", modules); 
            model.addAttribute("progressMap", progressMap);
            model.addAttribute("completedCount", completedCount);
            model.addAttribute("totalCount", totalCount);
            logger.info("Step 6 SUCCESS: Model attributes added.");

            logger.info("Data loaded successfully. Modules: {}, Progress: {}",
                    modules.size(), progress.size());

            logger.info("Step 7: Returning 'student-learning' view.");
            return "student-learning";

        } catch (Exception e) {
            logger.error("Error loading learning modules", e);
            // Redirect with an error message instead of trying to render a non-existent
            // page
            redirectAttributes.addFlashAttribute("error", "Could not load learning modules. Please try again later.");
            return "redirect:/student/dashboard"; // Redirect to a safe page like the dashboard
        }
    }

    @PostMapping("/learning/module/{moduleId}/complete")
    public String completeModule(Authentication authentication,
            @PathVariable Long moduleId,
            RedirectAttributes redirectAttributes) {
        logger.info("=== COMPLETE MODULE ENDPOINT CALLED for module {} ===", moduleId);

        try {
            Student student = studentService.getLoggedInStudent(authentication);

            logger.info("Completing module {} for student {}", moduleId, student.getId());
            learningModuleService.completeModule(student, moduleId);

            redirectAttributes.addFlashAttribute("success", "Module completed successfully!");
            return "redirect:/student/learning/module/" + moduleId;

        } catch (Exception e) {
            logger.error("Error completing module {}", moduleId, e);
            redirectAttributes.addFlashAttribute("error", "Error completing module: " + e.getMessage());
            return "redirect:/student/learning/module/" + moduleId;
        }
    }

    @GetMapping("/learning/module/{moduleId}")
    public String viewModule(Authentication authentication,
            @PathVariable Long moduleId,
            Model model,
            RedirectAttributes redirectAttributes) {

        logger.info("=== DEBUG START: Module {} ===", moduleId);

        try {
            // 1. Get student
            Student student = studentService.getLoggedInStudent(authentication);
            logger.info("DEBUG: Student: {}", student.getUsername());

            // 2. Get module
            LearningModule module = learningModuleService.getModuleById(moduleId);
            logger.info("DEBUG: Module: {}, Content null? {}",
                    module.getTitle(), module.getContent() == null);

            // 3. Get progress
            StudentProgress progress = learningModuleService.startModule(student, moduleId);
            logger.info("DEBUG: Progress: completed={}, quizViewed={}",
                    progress.getCompleted(), progress.getQuizViewed());

            // 4. Check quiz passed
            boolean hasPassed = quizService.hasPassedModule(student, module);
            logger.info("DEBUG: Has passed quiz? {}", hasPassed);

            // 5. Add attributes to model
            model.addAttribute("student", student);
            model.addAttribute("studentName", student.getFullName());
            model.addAttribute("module", module);
            model.addAttribute("moduleProgress", progress);
            model.addAttribute("isCompleted", progress.getCompleted());
            model.addAttribute("hasPassed", hasPassed);

            // 6. Safely add learning objectives list
            List<String> learningObjectivesList = module.getLearningObjectivesList();
            logger.info("Learning objectives list size: {}",
                    learningObjectivesList != null ? learningObjectivesList.size() : 0);
            model.addAttribute("learningObjectivesList",
                    learningObjectivesList != null ? learningObjectivesList : Collections.emptyList());

            // 7. Get quiz questions
            List<QuizQuestion> questions = learningModuleService.getQuizQuestions(moduleId);
            logger.info("Quiz questions found: {}", questions.size());

            // 8. Mark quiz as viewed if questions are present and not already viewed
            // FIXED: Add null check to prevent NPE
            if (!questions.isEmpty() && (progress.getQuizViewed() == null || !progress.getQuizViewed())) {
                learningModuleService.markQuizViewed(student, moduleId);
            }
            model.addAttribute("questions", questions != null ? questions : Collections.emptyList());

            // 9. Get quiz attempts history
            List<QuizAttempt> attempts = quizService.getAttemptsForModule(student, module);
            logger.info("Quiz attempts found for this module: {}", attempts.size());
            model.addAttribute("quizAttempts", attempts);

            logger.info("DEBUG: Returning module-detail template...");
            return "module-detail";

        } catch (RuntimeException e) {
            logger.error("=== RUNTIME ERROR in module detail endpoint ===", e);
            logger.error("Error message: {}", e.getMessage());

            if (e.getMessage() != null && e.getMessage().contains("Module not found")) {
                redirectAttributes.addFlashAttribute("error", "The requested learning module was not found.");
                return "redirect:/student/learning";
            } else if (e.getMessage() != null && e.getMessage().contains("Module is inactive")) {
                redirectAttributes.addFlashAttribute("error", "This module is currently unavailable.");
                return "redirect:/student/learning";
            }

            redirectAttributes.addFlashAttribute("error", "An error occurred while loading the module.");
            return "redirect:/student/learning";

        } catch (Exception e) {
            logger.error("=== GENERAL ERROR in module detail endpoint ===", e);
            logger.error("Error message: {}", e.getMessage());

            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
            return "redirect:/student/learning";
        }
    }

    @PostMapping("/learning/module/{moduleId}/quiz")
    public String submitQuiz(Authentication authentication,
            @PathVariable Long moduleId,
            @RequestParam Map<String, String> submittedAnswers,
            RedirectAttributes redirectAttributes) {
        logger.info("=== SUBMIT QUIZ ENDPOINT CALLED for module {} ===", moduleId);

        try {
            Student student = studentService.getLoggedInStudent(authentication);

            // Single transactional call - QuizService handles everything
            QuizAttempt attempt = quizService.submitQuiz(student, moduleId, submittedAnswers);

            // Flash message based on result
            if (attempt.isPassed()) {
                redirectAttributes.addFlashAttribute("success",
                        "Congratulations! You passed with a score of " + attempt.getScore() + "%.");
            } else {
                redirectAttributes.addFlashAttribute("error", "You did not pass the quiz. Your score was "
                        + attempt.getScore() + "%. A score of 80% is required to pass.");
            }
            return "redirect:/student/learning/module/" + moduleId;

        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("info", e.getMessage());
            return "redirect:/student/learning/module/" + moduleId;
        } catch (Exception e) {
            logger.error("Error submitting quiz for module {}", moduleId, e);
            redirectAttributes.addFlashAttribute("error", "Error submitting quiz: " + e.getMessage());
            return "redirect:/student/learning/module/" + moduleId;
        }
    }
}