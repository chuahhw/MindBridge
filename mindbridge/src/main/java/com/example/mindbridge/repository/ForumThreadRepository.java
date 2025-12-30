package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.ForumThread;

@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
    
    List<ForumThread> findAllByOrderByCreatedAtDesc();

    // Find recent forum threads 
    default List<ForumThread> findRecentForumThreads() {
        return findAllByOrderByCreatedAtDesc();
    }

    // Also fetch the reply authors to avoid lazy-loading issues in views
    @Query("SELECT DISTINCT t FROM ForumThread t LEFT JOIN FETCH t.replies r LEFT JOIN FETCH r.createdBy WHERE t.id = :id")
    ForumThread findByIdWithReplies(Long id);
}
