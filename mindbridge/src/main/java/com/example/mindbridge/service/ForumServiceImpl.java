package com.example.mindbridge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mindbridge.model.ForumReply;
import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.ForumReplyRepository;
import com.example.mindbridge.repository.ForumThreadRepository;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumThreadRepository threadRepository;

    @Autowired
    private ForumReplyRepository replyRepository;

    @Override
    public List<ForumThread> getAllThreads() {
        return threadRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public ForumThread getThreadById(Long id) {
        return threadRepository.findByIdWithReplies(id);
    }

    @Override
    public ForumThread createThread(String title, String content, User user, boolean anonymous) {
        ForumThread thread = new ForumThread(title, content, user, anonymous);
        return threadRepository.save(thread);
    }

    @Override
    public ForumThread likeThread(Long id) {
        ForumThread thread = threadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thread not found"));
        thread.setLikes(thread.getLikes() + 1);
        return threadRepository.save(thread);
    }

    @Override
    public ForumReply addReply(Long threadId, String content, User user) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new RuntimeException("Thread not found"));
        
        ForumReply reply = new ForumReply(content, user, thread);
        return replyRepository.save(reply);
    }
}