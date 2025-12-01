package com.example.mindbridge.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "learning_module")
public class LearningModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String duration;

    @Column(name = "display_order")
    private Integer displayOrder;

    private Boolean active = true;

    @Column(name = "learning_objectives", columnDefinition = "TEXT")
    private String learningObjectives;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizQuestion> quizQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentProgress> studentProgress = new ArrayList<>();

    // Constructors
    public LearningModule() {}

    public LearningModule(String category, String title, String description, String content, String duration, Integer displayOrder) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.content = content;
        this.duration = duration;
        this.displayOrder = displayOrder;
        this.active = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getLearningObjectives() { return learningObjectives; }
    public void setLearningObjectives(String learningObjectives) { this.learningObjectives = learningObjectives; }

    public List<QuizQuestion> getQuizQuestions() { return quizQuestions; }
    public void setQuizQuestions(List<QuizQuestion> quizQuestions) { this.quizQuestions = quizQuestions; }

    public List<StudentProgress> getStudentProgress() { return studentProgress; }
    public void setStudentProgress(List<StudentProgress> studentProgress) { this.studentProgress = studentProgress; }

    /**
     * Helper method to get learning objectives as a list of strings.
     * Assumes objectives are separated by newlines.
     */
    @Transient
    public List<String> getLearningObjectivesList() {
        if (this.learningObjectives == null || this.learningObjectives.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.learningObjectives.split("\\r?\\n"));
    }
}