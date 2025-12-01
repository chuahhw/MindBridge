package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    
    List<QuizQuestion> findByModuleIdOrderByIdAsc(Long moduleId);
    
    long countByModuleId(Long moduleId);

    // For admin purposes: delete all questions for a module
    void deleteAllByModuleId(Long moduleId);
}