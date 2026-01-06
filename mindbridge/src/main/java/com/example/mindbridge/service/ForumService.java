package com.example.mindbridge.service;

import java.util.List;

import com.example.mindbridge.model.ForumReply;
import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.User;

public interface ForumService {

    //Retrieve all thread from forum
    List<ForumThread> getAllThreads();

    //Get a single thread with its replies
    ForumThread getThreadById(Long id);

    //Create a new thread
    ForumThread createThread(String title, String content, User user, boolean anonymous);

    //Add reply or comment to an existing thread
    ForumReply addReply(Long threadId, String content, User user);
    ForumThread likeThread(Long id);
    void deleteThread(Long id);
}
