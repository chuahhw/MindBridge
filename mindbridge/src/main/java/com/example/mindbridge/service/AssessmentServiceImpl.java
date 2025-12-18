package com.example.mindbridge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.Question;
import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.AssessmentAttemptRepository;
import com.example.mindbridge.repository.QuestionSetRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentServiceImpl.class);

    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Autowired
    private AssessmentAttemptRepository assessmentAttemptRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // ====================== IMPLEMENT ALL METHODS ======================

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentAttempt> getPreviousAttempts(Student student) {
        logger.debug("getPreviousAttempts called for student ID: {}", student.getId());

        try {
            // Convert Student to User
            User user = entityManager.find(User.class, student.getId());
            if (user == null) {
                logger.warn("User not found for student ID: {}", student.getId());
                return new ArrayList<>();
            }

            logger.debug("Found user: {}", user.getId());

            // Use the repository method
            List<AssessmentAttempt> attempts = assessmentAttemptRepository.findByUserOrderByAttemptDateDesc(user);

            logger.debug("Found {} previous attempts", attempts.size());
            return attempts;

        } catch (Exception e) {
            logger.error("ERROR in getPreviousAttempts: {}", e.getMessage(), e);
            return new ArrayList<>(); // Return empty list instead of throwing
        }
    }

    private void validateStudent(Student student) {
        User user = entityManager.find(User.class, student.getId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!"STUDENT".equals(user.getUserType())) {
            throw new RuntimeException("Only students can take assessments");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionSet getQuestionSetByName(String setName) {
        logger.debug("Getting QuestionSet: {}", setName);

        try {
            // Simple query to avoid MultipleBagFetchException
            String jpql = "SELECT qs FROM QuestionSet qs WHERE qs.name = :name";
            QuestionSet questionSet = entityManager.createQuery(jpql, QuestionSet.class)
                    .setParameter("name", setName)
                    .getSingleResult();

            // Load questions separately to avoid MultipleBagFetchException
            String questionsJpql = "SELECT DISTINCT q FROM Question q " +
                    "LEFT JOIN FETCH q.answerOptions " +
                    "WHERE q.questionSet.id = :setId " +
                    "ORDER BY q.questionOrder";

            List<Question> questions = entityManager.createQuery(questionsJpql, Question.class)
                    .setParameter("setId", questionSet.getId())
                    .getResultList();

            questionSet.setQuestions(questions);
            return questionSet;

        } catch (Exception e) {
            logger.error("ERROR loading QuestionSet: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load QuestionSet: " + setName, e);
        }
    }

    @Override
    @Transactional
    public AssessmentAttempt saveAssessment(Student student, String setName, Map<String, String> answers) {
        validateStudent(student);
        logger.debug("Saving assessment for student: {}. Question set: {}", student.getFullName(), setName);

        try {
            // Get the User entity (not Student)
            User user = entityManager.find(User.class, student.getId());
            if (user == null) {
                throw new RuntimeException("User not found for student: " + student.getId());
            }

            // Get QuestionSet
            QuestionSet questionSet = getQuestionSetByName(setName);

            // Calculate score
            int totalScore = calculateScore(answers);
            
            // Determine severity level based on assessment type and score
            String severityLevel = calculateSeverityLevel(setName, totalScore);

            // Create attempt
            AssessmentAttempt attempt = new AssessmentAttempt();
            attempt.setUser(user); // Use User, not Student
            attempt.setQuestionSet(questionSet);
            attempt.setTotalScore(totalScore);
            attempt.setSeverityLevel(severityLevel);
            // attemptDate is automatically set by @PrePersist

            logger.debug("Attempt created with score: {}, severity: {}", attempt.getTotalScore(), attempt.getSeverityLevel());

            // Save and return
            return assessmentAttemptRepository.save(attempt);

        } catch (Exception e) {
            logger.error("ERROR in saveAssessment: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save assessment: " + e.getMessage(), e);
        }
    }

    private int calculateScore(Map<String, String> answers) {
        int totalScore = 0;
        for (String value : answers.values()) {
            try {
                totalScore += Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }
        return totalScore;
    }

    private String calculateSeverityLevel(String assessmentName, int score) {
        if ("PHQ-9".equalsIgnoreCase(assessmentName)) {
            return calculatePHQ9Severity(score);
        } else if ("GAD-7".equalsIgnoreCase(assessmentName)) {
            return calculateGAD7Severity(score);
        } else {
            // Default severity calculation for other assessments
            return calculateGeneralSeverity(score);
        }
    }

    private String calculatePHQ9Severity(int score) {
        // PHQ-9 scoring guidelines:
        // 0-4: Minimal depression
        // 5-9: Mild depression
        // 10-14: Moderate depression
        // 15-19: Moderately severe depression
        // 20-27: Severe depression
        
        if (score >= 20) {
            return "Severe Depression";
        } else if (score >= 15) {
            return "Moderately Severe Depression";
        } else if (score >= 10) {
            return "Moderate Depression";
        } else if (score >= 5) {
            return "Mild Depression";
        } else {
            return "Minimal Depression";
        }
    }

    private String calculateGAD7Severity(int score) {
        // GAD-7 scoring guidelines:
        // 0-4: Minimal anxiety
        // 5-9: Mild anxiety
        // 10-14: Moderate anxiety
        // 15-21: Severe anxiety
        
        if (score >= 15) {
            return "Severe Anxiety";
        } else if (score >= 10) {
            return "Moderate Anxiety";
        } else if (score >= 5) {
            return "Mild Anxiety";
        } else {
            return "Minimal Anxiety";
        }
    }

    private String calculateGeneralSeverity(int score) {
        // Default severity calculation for other assessments
        // Assuming max score of 100 (as defined in getMaxScoreForSet)
        int maxScore = 100;
        double percentage = (score * 100.0) / maxScore;
        
        if (percentage >= 80) {
            return "High Severity";
        } else if (percentage >= 60) {
            return "Moderate-High Severity";
        } else if (percentage >= 40) {
            return "Moderate Severity";
        } else if (percentage >= 20) {
            return "Mild Severity";
        } else {
            return "Minimal Severity";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AssessmentAttempt getAssessmentAttemptById(Integer attemptId) {
        try {
            return assessmentAttemptRepository.findById(attemptId)
                    .orElseThrow(() -> new RuntimeException("AssessmentAttempt not found with ID: " + attemptId));
        } catch (Exception e) {
            logger.error("ERROR getting attempt by ID: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int getMaxScoreForSet(String setName) {
        // Return actual max scores for each assessment
        if ("PHQ-9".equalsIgnoreCase(setName)) {
            return 27; // PHQ-9 max score (9 questions * 3 points each)
        } else if ("GAD-7".equalsIgnoreCase(setName)) {
            return 21; // GAD-7 max score (7 questions * 3 points each)
        } else {
            return 100; // Default for other assessments
        }
    }

    // ====================== HELPER METHODS ======================

    @Transactional(readOnly = true)
    public QuestionSet getQuestionSetSimple(String setName) {
        // Alternative: Use repository method without JOIN FETCH
        Optional<QuestionSet> optional = questionSetRepository.findByName(setName);
        if (optional.isPresent()) {
            QuestionSet qs = optional.get();

            // Load questions with options in a separate query
            String jpql = "SELECT DISTINCT q FROM Question q " +
                    "LEFT JOIN FETCH q.answerOptions " +
                    "WHERE q.questionSet.id = :setId " +
                    "ORDER BY q.questionOrder";

            List<Question> questions = entityManager.createQuery(jpql, Question.class)
                    .setParameter("setId", qs.getId())
                    .getResultList();

            qs.setQuestions(questions);
            return qs;
        }
        throw new RuntimeException("QuestionSet not found: " + setName);
    }
    
    // Optional: Add a method to get severity interpretation details
    public String getSeverityInterpretation(String assessmentName, int score) {
        String severity = calculateSeverityLevel(assessmentName, score);
        
        if ("PHQ-9".equalsIgnoreCase(assessmentName)) {
            switch (severity) {
                case "Minimal Depression":
                    return "Your score suggests minimal or no depression symptoms.";
                case "Mild Depression":
                    return "Your score suggests mild depression. Monitor your mood and consider self-care strategies.";
                case "Moderate Depression":
                    return "Your score suggests moderate depression. Consider speaking with a healthcare professional.";
                case "Moderately Severe Depression":
                    return "Your score suggests moderately severe depression. It is recommended to consult with a healthcare professional.";
                case "Severe Depression":
                    return "Your score suggests severe depression. Please seek professional help as soon as possible.";
            }
        } else if ("GAD-7".equalsIgnoreCase(assessmentName)) {
            switch (severity) {
                case "Minimal Anxiety":
                    return "Your score suggests minimal or no anxiety symptoms.";
                case "Mild Anxiety":
                    return "Your score suggests mild anxiety. Consider stress management techniques.";
                case "Moderate Anxiety":
                    return "Your score suggests moderate anxiety. Consider speaking with a healthcare professional.";
                case "Severe Anxiety":
                    return "Your score suggests severe anxiety. It is recommended to consult with a healthcare professional.";
            }
        }
        
        return "Assessment results should be discussed with a healthcare professional for proper interpretation.";
    }
}