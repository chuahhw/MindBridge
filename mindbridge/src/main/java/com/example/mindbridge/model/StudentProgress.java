package com.example.mindbridge.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "student_progress", 
       uniqueConstraints = { @UniqueConstraint(columnNames = {"student_id", "module_id"}) },
       indexes = {
           @Index(name = "idx_student_module", columnList = "student_id, module_id"),
           @Index(name = "idx_student_completed", columnList = "student_id, completed")
})
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private LearningModule module;

    @Column(nullable = false)
    private Boolean completed = false;

    private LocalDateTime completedAt;

    private LocalDateTime lastAccessed;

    @Column(name = "quiz_score")
    private Integer quizScore;

    @Column(name = "quiz_viewed")
    private Boolean quizViewed = false;

    // Constructors
    public StudentProgress() {
        this.lastAccessed = LocalDateTime.now();
    }

    public StudentProgress(Student student, LearningModule module) {
        this();
        this.student = student;
        this.module = module;
        this.completed = false;
        this.quizScore = null;
        this.quizViewed = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LearningModule getModule() { return module; }
    public void setModule(LearningModule module) { this.module = module; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public LocalDateTime getLastAccessed() { return lastAccessed; }
    public void setLastAccessed(LocalDateTime lastAccessed) { this.lastAccessed = lastAccessed; }

    public Integer getQuizScore() { return quizScore; }
    public void setQuizScore(Integer quizScore) { this.quizScore = quizScore; }

    public Boolean getQuizViewed() { return quizViewed; }
    public void setQuizViewed(Boolean quizViewed) { this.quizViewed = quizViewed; }
}