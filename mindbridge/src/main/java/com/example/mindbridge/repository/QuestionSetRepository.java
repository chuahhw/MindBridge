package com.example.mindbridge.repository;

import com.example.mindbridge.model.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface QuestionSetRepository extends JpaRepository<QuestionSet, Integer> {
    
    // 1. Simple find by name (no JOIN FETCH)
    // Spring will generate: SELECT qs FROM QuestionSet qs WHERE qs.name = ?1
    Optional<QuestionSet> findByName(String name);
    
    // 2. Find with questions only
    @Query("SELECT DISTINCT qs FROM QuestionSet qs " +
           "LEFT JOIN FETCH qs.questions q " +
           "WHERE qs.name = :name " +
           "ORDER BY q.questionOrder")
    Optional<QuestionSet> findByNameWithQuestions(@Param("name") String name);
}