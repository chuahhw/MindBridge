package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.ForumReply;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {

    List<ForumReply> findByThreadIdOrderByCreatedAtAsc(Long threadId);

    Long countByThreadId(Long threadId);
}
