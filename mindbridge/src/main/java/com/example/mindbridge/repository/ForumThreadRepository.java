package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.ForumThread;

@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
    
    List<ForumThread> findAllByOrderByCreatedAtDesc();

    // Find recent forum threads (alias for the existing method)
    default List<ForumThread> findRecentForumThreads() {
        return findAllByOrderByCreatedAtDesc();
    }

    @Query("SELECT t FROM ForumThread t LEFT JOIN FETCH t.replies WHERE t.id = :id")
    ForumThread findByIdWithReplies(Long id);
}
