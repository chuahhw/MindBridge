package com.example.mindbridge.repository;

import com.example.mindbridge.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
    @Query("SELECT DISTINCT q FROM Question q " +
           "LEFT JOIN FETCH q.answerOptions ao " +
           "WHERE q.questionSet.id = :questionSetId " +
           "ORDER BY q.questionOrder, ao.id")
    List<Question> findQuestionsWithOptionsByQuestionSetId(@Param("questionSetId") Integer questionSetId);
}