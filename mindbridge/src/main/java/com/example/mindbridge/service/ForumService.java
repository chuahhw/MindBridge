package com.example.mindbridge.service;

import java.util.List;

import com.example.mindbridge.model.ForumReply;
import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.User;

public interface ForumService {
    List<ForumThread> getAllThreads();
    ForumThread getThreadById(Long id);
    ForumThread createThread(String title, String content, User user, boolean anonymous);
    ForumReply addReply(Long threadId, String content, User user);
    ForumThread likeThread(Long id);
    void deleteThread(Long id);
}
