package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.User;

public interface AssessmentAttemptRepository extends JpaRepository<AssessmentAttempt, Integer> {
    
    // Method 1: Use exact property name "attemptDate"
    List<AssessmentAttempt> findByUserOrderByAttemptDateDesc(User user);
    
    // Method 2: Or use @Query for more control
    @Query("SELECT a FROM AssessmentAttempt a WHERE a.user = :user ORDER BY a.attemptDate DESC")
    List<AssessmentAttempt> findAttemptsByUser(@Param("user") User user);
    
    // Method 3: Simple method that should work
    List<AssessmentAttempt> findByUser(User user);

    // Find recent assessment attempts across all users
    List<AssessmentAttempt> findAllByOrderByAttemptDateDesc();
}
