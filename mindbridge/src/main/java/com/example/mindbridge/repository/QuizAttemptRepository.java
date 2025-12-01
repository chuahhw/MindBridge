package com.example.mindbridge.repository;

import com.example.mindbridge.model.QuizAttempt;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.LearningModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    
    // Find all quiz attempts by a student
    List<QuizAttempt> findByStudentOrderByAttemptTimeDesc(Student student);
    
    // Find quiz attempts for a specific module by a student
    List<QuizAttempt> findByStudentAndModuleOrderByAttemptTimeDesc(Student student, LearningModule module);
    
    // Find the latest quiz attempt for a module by a student
    Optional<QuizAttempt> findTopByStudentAndModuleOrderByAttemptTimeDesc(Student student, LearningModule module);
    
    // Find all passed quiz attempts for a student
    List<QuizAttempt> findByStudentAndPassedTrueOrderByAttemptTimeDesc(Student student);
    
    // Find if student has passed a specific module
    boolean existsByStudentAndModuleAndPassedTrue(Student student, LearningModule module);
    
    // Count total quiz attempts by a student
    long countByStudent(Student student);
    
    // Count passed quiz attempts by a student
    long countByStudentAndPassedTrue(Student student);
    
    // Find best score for a module by a student
    @Query("SELECT MAX(qa.score) FROM QuizAttempt qa WHERE qa.student = :student AND qa.module = :module")
    Optional<Integer> findBestScoreByStudentAndModule(@Param("student") Student student, 
                                                     @Param("module") LearningModule module);
    
    // Calculate average score for a student
    @Query("SELECT AVG(qa.score) FROM QuizAttempt qa WHERE qa.student = :student")
    Optional<Double> findAverageScoreByStudent(@Param("student") Student student);
    
    // Find all quiz attempts for a specific module (for admin purposes)
    List<QuizAttempt> findByModuleOrderByAttemptTimeDesc(LearningModule module);
    
    // Count attempts for a specific module
    long countByModule(LearningModule module);
    
    // Find recent quiz attempts (for dashboard)
    List<QuizAttempt> findFirst5ByStudentOrderByAttemptTimeDesc(Student student);
}