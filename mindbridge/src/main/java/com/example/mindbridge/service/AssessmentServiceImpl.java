package com.example.mindbridge.service;

import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.Question;
import com.example.mindbridge.model.User; // Add this import
import com.example.mindbridge.repository.QuestionSetRepository;
import com.example.mindbridge.repository.UserRepository;
import com.example.mindbridge.repository.QuestionRepository;
import com.example.mindbridge.repository.AssessmentAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository; // Add this line

    @Autowired
    private AssessmentAttemptRepository assessmentAttemptRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // ====================== IMPLEMENT ALL METHODS ======================

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentAttempt> getPreviousAttempts(Student student) {
        System.out.println("DEBUG: getPreviousAttempts called for student ID: " + student.getId());

        try {
            // Convert Student to User
            User user = entityManager.find(User.class, student.getId());
            if (user == null) {
                System.out.println("DEBUG: User not found for student ID: " + student.getId());
                return new ArrayList<>();
            }

            System.out.println("DEBUG: Found user: " + user.getId());

            // Use the repository method
            List<AssessmentAttempt> attempts = assessmentAttemptRepository.findByUserOrderByAttemptDateDesc(user);

            System.out.println("DEBUG: Found " + attempts.size() + " previous attempts");
            return attempts;

        } catch (Exception e) {
            System.err.println("ERROR in getPreviousAttempts: " + e.getMessage());
            e.printStackTrace();
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
        System.out.println("DEBUG: Getting QuestionSet: " + setName);

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
            System.err.println("ERROR loading QuestionSet: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to load QuestionSet: " + setName, e);
        }
    }

    @Override
    @Transactional
    public AssessmentAttempt saveAssessment(Student student, String setName, Map<String, String> answers) {
        validateStudent(student);
        System.out.println("DEBUG: Saving assessment for student: " + student.getFullName());
        System.out.println("DEBUG: Question set: " + setName);

        try {
            // Get the User entity (not Student)
            User user = entityManager.find(User.class, student.getId());
            if (user == null) {
                throw new RuntimeException("User not found for student: " + student.getId());
            }

            // Get QuestionSet
            QuestionSet questionSet = getQuestionSetByName(setName);

            // Create attempt
            AssessmentAttempt attempt = new AssessmentAttempt();
            attempt.setUser(user); // Use User, not Student
            attempt.setQuestionSet(questionSet);
            attempt.setTotalScore(calculateScore(answers));
            attempt.setSeverityLevel("Normal");
            // attemptDate is automatically set by @PrePersist

            System.out.println("DEBUG: Attempt created with score: " + attempt.getTotalScore());

            // Save and return
            return assessmentAttemptRepository.save(attempt);

        } catch (Exception e) {
            System.err.println("ERROR in saveAssessment: " + e.getMessage());
            e.printStackTrace();
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

    @Override
    @Transactional(readOnly = true)
    public AssessmentAttempt getAssessmentAttemptById(Integer attemptId) {
        try {
            return assessmentAttemptRepository.findById(attemptId)
                    .orElseThrow(() -> new RuntimeException("AssessmentAttempt not found with ID: " + attemptId));
        } catch (Exception e) {
            System.err.println("ERROR getting attempt by ID: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int getMaxScoreForSet(String setName) {
        // Default value
        return 100;
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
}