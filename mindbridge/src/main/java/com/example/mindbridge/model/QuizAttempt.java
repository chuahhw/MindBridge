package com.example.mindbridge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {

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
    private Integer score;

    @Column(nullable = false)
    private boolean passed;

    @Column(name = "attempt_time", nullable = false)
    private LocalDateTime attemptTime;

    @Column(columnDefinition = "TEXT")
    private String answers; // Optional: store submitted answers as JSON or CSV

    @PrePersist
    protected void onCreate() {
        this.attemptTime = LocalDateTime.now();
    }

    // Constructors, getters, and setters
    public QuizAttempt() {}

    public QuizAttempt(Student student, LearningModule module, Integer score, boolean passed, String answers) {
        this.student = student;
        this.module = module;
        this.score = score;
        this.passed = passed;
        this.answers = answers;
    }

    // Getters and setters for all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public LearningModule getModule() { return module; }
    public void setModule(LearningModule module) { this.module = module; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }
    public LocalDateTime getAttemptTime() { return attemptTime; }
    public void setAttemptTime(LocalDateTime attemptTime) { this.attemptTime = attemptTime; }
    public String getAnswers() { return answers; }
    public void setAnswers(String answers) { this.answers = answers; }
}