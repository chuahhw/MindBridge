package com.example.mindbridge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.repository.ForumReplyRepository;
import com.example.mindbridge.service.ForumService;
import com.example.mindbridge.service.LearningModuleService;

@Controller
@RequestMapping("/admin")
public class AdminContentController {

    // Inner class to hold thread information for admin display
    public static class ThreadInfo {
        private ForumThread thread;
        private int replyCount;
        private String formattedDate;

        public ThreadInfo() {}

        public ForumThread getThread() { return thread; }
        public void setThread(ForumThread thread) { this.thread = thread; }

        public int getReplyCount() { return replyCount; }
        public void setReplyCount(int replyCount) { this.replyCount = replyCount; }

        public String getFormattedDate() { return formattedDate; }
        public void setFormattedDate(String formattedDate) { this.formattedDate = formattedDate; }
    }

    private final LearningModuleService learningModuleService;
    private final ForumService forumService;
    private ForumReplyRepository replyRepository;

    @Autowired
    public void setReplyRepository(ForumReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public AdminContentController(LearningModuleService learningModuleService, ForumService forumService) {
        this.learningModuleService = learningModuleService;
        this.forumService = forumService;
    }

    @GetMapping("/content")
    public String adminContent(Model model) {

        List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
        if (modules == null) modules = new ArrayList<>();
        System.out.println("DEBUG: Found " + modules.size() + " learning modules");

        List<ForumThread> threads = forumService.getAllThreads();
        if (threads == null) {
            threads = new ArrayList<>();
            System.out.println("DEBUG: threads was null, initialized empty list");
        } else {
            System.out.println("DEBUG: Found " + threads.size() + " forum threads");
            // Log some details about the threads
            threads.forEach(thread -> {
                System.out.println("DEBUG: Thread ID: " + thread.getId() + ", Title: " + thread.getTitle());
            });
        }

        // Prepare thread data with reply counts for template
        List<ThreadInfo> threadInfos = threads.stream().map(thread -> {
            ThreadInfo info = new ThreadInfo();
            info.setThread(thread);

            // Get reply count directly from repository
            Long replyCount = replyRepository.countByThreadId(thread.getId());
            info.setReplyCount(replyCount != null ? replyCount.intValue() : 0);
            System.out.println("DEBUG: Thread " + thread.getId() + " has " + replyCount + " replies");

            info.setFormattedDate(thread.getCreatedAt() != null ?
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(thread.getCreatedAt())
                : "N/A");
            return info;
        }).toList();

        model.addAttribute("modules", modules);
        model.addAttribute("threadInfos", threadInfos);

        return "admin-content";
    }

    @GetMapping("/content/{id}/edit")
    public String editModuleModal(@PathVariable Long id, Model model) {
        List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
        model.addAttribute("modules", modules);
        // Find module including inactive ones
        LearningModule editModule = modules.stream()
            .filter(module -> module.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));
        model.addAttribute("editModule", editModule);
        model.addAttribute("showModal", true);
        return "admin-content";
    }

    @PostMapping("/content/{id}/edit")
    public String updateModuleForm(@PathVariable Long id,
                                  @RequestParam("title") String title,
                                  @RequestParam("category") String category,
                                  @RequestParam("description") String description,
                                  @RequestParam(value = "content", required = false) String content,
                                  @RequestParam(value = "duration", required = false) String duration,
                                  @RequestParam(value = "displayOrder", defaultValue = "0") Integer displayOrder,
                                  @RequestParam(value = "learningObjectives", required = false) String learningObjectives,
                                  @RequestParam(value = "active", defaultValue = "true") Boolean active,
                                  Model model) {
        try {
            // Find module including inactive ones
            List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
            LearningModule module = modules.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));

            module.setTitle(title);
            module.setCategory(category);
            module.setDescription(description);
            module.setContent(content);
            module.setDuration(duration);
            module.setDisplayOrder(displayOrder);
            module.setLearningObjectives(learningObjectives);
            module.setActive(active);

            learningModuleService.updateModule(module);
            return "redirect:/admin/content";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while updating the module. Please try again.");
            return loadEditModal(id, model);
        }
    }

    private String loadEditModal(Long id, Model model) {
        List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
        model.addAttribute("modules", modules);
        // Find module including inactive ones
        LearningModule editModule = modules.stream()
            .filter(module -> module.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));
        model.addAttribute("editModule", editModule);
        model.addAttribute("showModal", true);
        return "admin-content";
    }

    @DeleteMapping("/content/{id}")
    public String toggleModuleActiveStatus(@PathVariable Long id) {
        try {
            // Find module including inactive ones
            List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
            LearningModule module = modules.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));

            // Toggle active status
            module.setActive(!module.getActive());
            learningModuleService.updateModule(module);
            return "redirect:/admin/content";
        } catch (Exception e) {
            // Handle error - could add error message
            return "redirect:/admin/content";
        }
    }

    @DeleteMapping("/content/forum/{id}")
    public String deleteForumThread(@PathVariable Long id) {
        try {
            forumService.deleteThread(id);
            return "redirect:/admin/content";
        } catch (Exception e) {
            // Handle error - could add error message
            return "redirect:/admin/content";
        }
    }

    @DeleteMapping("/content/forum/reply/{replyId}")
    public String deleteForumReply(@PathVariable Long replyId) {
        try {
            replyRepository.deleteById(replyId);
            System.out.println("DEBUG: Deleted reply with ID: " + replyId);
            return "redirect:/admin/content";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/content";
        }
    }

    @GetMapping("/content/forum/{threadId}/view")
    public String viewForumThreadModal(@PathVariable Long threadId, Model model) {
        try {
            // Always get thread with replies loaded
            ForumThread viewThread = forumService.getThreadById(threadId);

            if (viewThread == null) {
                System.out.println("DEBUG: Thread not found with ID: " + threadId);
                return "redirect:/admin/content";
            }

            System.out.println("DEBUG: Found thread for modal: " + viewThread.getTitle());
            System.out.println("DEBUG: Thread has replies: " + (viewThread.getReplies() != null ? viewThread.getReplies().size() : "null"));
            if (viewThread.getReplies() != null && !viewThread.getReplies().isEmpty()) {
                System.out.println("DEBUG: First reply content: " + viewThread.getReplies().get(0).getContent());
            }

            // Add all existing model attributes
            List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
            model.addAttribute("modules", modules);

            // Prepare thread data for template (reload all threads without replies for efficiency)
            List<ForumThread> allThreads = forumService.getAllThreads();
            List<ThreadInfo> threadInfos = allThreads.stream().map(thread -> {
                ThreadInfo info = new ThreadInfo();
                info.setThread(thread);
                Long replyCount = replyRepository.countByThreadId(thread.getId());
                info.setReplyCount(replyCount != null ? replyCount.intValue() : 0);
                info.setFormattedDate(thread.getCreatedAt() != null ?
                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(thread.getCreatedAt())
                    : "N/A");
                return info;
            }).toList();

            model.addAttribute("threadInfos", threadInfos);
            model.addAttribute("viewThread", viewThread);
            model.addAttribute("showThreadViewModal", true);
            return "admin-content";

        } catch (Exception e) {
            System.out.println("DEBUG: Error in viewForumThreadModal: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/admin/content";
        }
    }
}
