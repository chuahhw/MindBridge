package com.example.mindbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.User;
import com.example.mindbridge.service.ForumService;
import com.example.mindbridge.service.UserService;

@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    // Single forum for both students and counselors
    @GetMapping("/forum")
    public String forum(Authentication authentication, Model model) {
        User user = userService.getLoggedInUser(authentication);
        model.addAttribute("user", user);
        // For student header fragment
        model.addAttribute("studentName", user.getFullName());
        model.addAttribute("threads", forumService.getAllThreads());
        model.addAttribute("isCounselor", user.getRole().equals("COUNSELOR"));
        return "forum";
    }

    // Single thread view for both roles
    @GetMapping("/forum/{threadId}")
    public String viewThread(@PathVariable Long threadId, Authentication authentication, Model model) {
        User user = userService.getLoggedInUser(authentication);
        ForumThread thread = forumService.getThreadById(threadId);
        
        model.addAttribute("user", user);
        model.addAttribute("studentName", user.getFullName());
        model.addAttribute("thread", thread);
        model.addAttribute("isCounselor", user.getRole().equals("COUNSELOR"));
        return "thread";
    }

    // Create new thread - both roles can create
    @PostMapping("/forum/thread")
    public String createThread(@RequestParam String title, 
                              @RequestParam String content,
                               @RequestParam(name = "anonymous", defaultValue = "false") boolean anonymous,
                              Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        forumService.createThread(title, content, user, anonymous);
        return "redirect:/forum";
    }

    // Add reply to thread - both roles can reply
    @PostMapping("/forum/thread/{threadId}/reply")
    public String addReply(@PathVariable Long threadId,
                          @RequestParam String content,
                          Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        forumService.addReply(threadId, content, user);
        return "redirect:/forum#thread-" + threadId;
    }

    // Like a thread
    @PostMapping("/forum/thread/{threadId}/like")
    public String likeThread(@PathVariable Long threadId) {
        forumService.likeThread(threadId);
        return "redirect:/forum#thread-" + threadId;
    }
}