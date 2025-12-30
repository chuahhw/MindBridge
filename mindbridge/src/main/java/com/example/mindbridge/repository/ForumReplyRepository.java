package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.ForumReply;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {

    @Query("SELECT r FROM ForumReply r LEFT JOIN FETCH r.createdBy WHERE r.thread.id = :threadId ORDER BY r.createdAt ASC")
    List<ForumReply> findByThreadIdOrderByCreatedAtAsc(Long threadId);

    Long countByThreadId(Long threadId);
}
