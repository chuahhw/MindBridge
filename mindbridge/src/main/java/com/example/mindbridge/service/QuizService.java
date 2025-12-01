package com.example.mindbridge.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.model.QuizAttempt;
import com.example.mindbridge.model.QuizQuestion;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.StudentProgress;
import com.example.mindbridge.repository.QuizAttemptRepository;
import com.example.mindbridge.repository.StudentProgressRepository;
import com.example.mindbridge.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    private final QuizAttemptRepository quizAttemptRepository;
    private final LearningModuleService learningModuleService;
    private final StudentProgressRepository progressRepository;

    @Autowired
    public QuizService(QuizAttemptRepository quizAttemptRepository, LearningModuleService learningModuleService,
            StudentProgressRepository progressRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
        this.learningModuleService = learningModuleService;
        this.progressRepository = progressRepository;
    }

    @PersistenceContext
    private EntityManager entityManager; 

    private void validateStudent(Student student) {
        // Since you're already using User entity in AssessmentService,
        // you can use entityManager here too
        User user = entityManager.find(User.class, student.getId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!"STUDENT".equals(user.getUserType())) {
            throw new RuntimeException("Only students can take quizzes");
        }
    }

    /**
     * Submit quiz with answers from a map (typically from a web form).
     * This is the primary method to be called from the controller.
     */
    @Transactional
    public QuizAttempt submitQuiz(Student student, Long moduleId, Map<String, String> submittedAnswers) {
        validateStudent(student);
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        // Prevent re-submission if already passed
        if (hasPassedModule(student, learningModuleService.getModuleById(moduleId))) {
            throw new IllegalStateException("You have already passed this module's quiz.");
        }

        try {
            LearningModule module = learningModuleService.getModuleById(moduleId);
            List<QuizQuestion> questions = learningModuleService.getQuizQuestions(moduleId);

            int score = calculateScore(questions, submittedAnswers);
            boolean passed = score >= 80; // Passing score of 80%

            // Create and save the quiz attempt record
            QuizAttempt attempt = new QuizAttempt();
            attempt.setStudent(student);
            attempt.setModule(module);
            attempt.setScore(score);
            attempt.setPassed(passed);
            QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);

            // If the user passed, complete the module within the same transaction.
            if (passed) {
                updateModuleProgress(student, module, score);
            }

            return savedAttempt;
        } catch (Exception e) {
            logger.error("Error submitting quiz for student {} module {}", student.getId(), moduleId, e);
            throw new RuntimeException("Could not submit quiz: " + e.getMessage(), e);
        }
    }

    /**
     * Updates the student's progress for a module. This is called within the
     * submitQuiz transaction.
     */
    @Transactional
    protected void updateModuleProgress(Student student, LearningModule module, Integer score) {
        StudentProgress progress = progressRepository
                .findByStudentAndModule(student, module)
                .orElseGet(() -> new StudentProgress(student, module));

        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());
        progress.setQuizScore(score);
        progressRepository.save(progress);
    }

    private int calculateScore(List<QuizQuestion> questions, Map<String, String> submittedAnswers) {
        if (questions.isEmpty()) {
            return 0;
        }
        long correctCount = questions.stream().filter(q -> {
            String submittedAnswer = submittedAnswers.get("question-" + q.getId());
            return submittedAnswer != null && q.getCorrectAnswerIndex() == Integer.parseInt(submittedAnswer);
        }).count();
        return (int) Math.round(((double) correctCount / questions.size()) * 100);
    }

    @Transactional(readOnly = true)
    public boolean hasPassedModule(Student student, LearningModule module) {
        try {
            // Add null check to prevent NullPointerException
            if (student == null || module == null) {
                return false;
            }
            return quizAttemptRepository.existsByStudentAndModuleAndPassedTrue(student, module);
        } catch (Exception e) {
            logger.error("Error checking passed state for student {} module {}",
                    student != null ? student.getId() : "null",
                    module != null ? module.getId() : "null", e);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Integer> getBestScore(Student student, LearningModule module) {
        try {
            return quizAttemptRepository.findBestScoreByStudentAndModule(student, module);
        } catch (Exception e) {
            logger.error("Error fetching best score for student {} module {}",
                    student != null ? student.getId() : "null",
                    module != null ? module.getId() : "null", e);
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    public List<QuizAttempt> getStudentAttempts(Student student) {
        try {
            return quizAttemptRepository.findByStudentOrderByAttemptTimeDesc(student);
        } catch (Exception e) {
            logger.error("Error fetching attempts for student {}", student != null ? student.getId() : "null", e);
            return List.of();
        }
    }

    @Transactional(readOnly = true)
    public List<QuizAttempt> getModuleAttempts(Student student, LearningModule module) {
        try {
            return quizAttemptRepository.findByStudentAndModuleOrderByAttemptTimeDesc(student, module);
        } catch (Exception e) {
            logger.error("Error fetching module attempts for student {} module {}",
                    student != null ? student.getId() : "null",
                    module != null ? module.getId() : "null", e);
            return List.of();
        }
    }

    @Transactional(readOnly = true)
    public Optional<QuizAttempt> getLatestAttempt(Student student, LearningModule module) {
        try {
            return quizAttemptRepository.findTopByStudentAndModuleOrderByAttemptTimeDesc(student, module);
        } catch (Exception e) {
            logger.error("Error fetching latest attempt for student {} module {}",
                    student != null ? student.getId() : "null",
                    module != null ? module.getId() : "null", e);
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    public List<QuizAttempt> getAttemptsForModule(Student student, LearningModule module) {
        try {
            return quizAttemptRepository.findByStudentAndModuleOrderByAttemptTimeDesc(student, module);
        } catch (Exception e) {
            logger.error("Error fetching attempts for student {} and module {}",
                    student != null ? student.getId() : "null",
                    module != null ? module.getId() : "null", e);
            return List.of();
        }
    }
}
