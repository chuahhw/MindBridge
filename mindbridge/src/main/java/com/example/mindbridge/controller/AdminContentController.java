package com.example.mindbridge.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.service.LearningModuleService;

@Controller
@RequestMapping("/admin")
public class AdminContentController {

    private final LearningModuleService learningModuleService;

    public AdminContentController(LearningModuleService learningModuleService) {
        this.learningModuleService = learningModuleService;
    }

    @GetMapping("/content")
    public String adminContent(Model model) {
        List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
        model.addAttribute("modules", modules);
        return "admin-content";
    }

    @GetMapping("/content/{id}/edit")
    public String editModuleModal(@PathVariable Long id, Model model) {
        List<LearningModule> modules = learningModuleService.getAllModulesIncludingInactive();
        model.addAttribute("modules", modules);
        model.addAttribute("editModule", learningModuleService.getModuleById(id));
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
            LearningModule module = learningModuleService.getModuleById(id);
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
        model.addAttribute("editModule", learningModuleService.getModuleById(id));
        model.addAttribute("showModal", true);
        return "admin-content";
    }

    @DeleteMapping("/content/{id}")
    public String deleteModule(@PathVariable Long id) {
        try {
            LearningModule module = learningModuleService.getModuleById(id); // This allows inactive modules to be retrieved
            module.setActive(false);
            learningModuleService.updateModule(module);
            return "redirect:/admin/content";
        } catch (Exception e) {
            // Handle error - could add error message
            return "redirect:/admin/content";
        }
    }
}
