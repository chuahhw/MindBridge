package com.example.mindbridge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assessment_attempt")
public class AssessmentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_set_id", nullable = false)
    private QuestionSet questionSet;

    @Column(name = "attempt_date")
    private LocalDateTime attemptDate;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "severity_level", length = 50)
    private String severityLevel;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL)
    private List<AssessmentAnswer> answers;

    @PrePersist
    protected void onCreate() {
        attemptDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public QuestionSet getQuestionSet() {
        return questionSet;
    }
    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }
    public LocalDateTime getAttemptDate() {
        return attemptDate;
    }
    public void setAttemptDate(LocalDateTime attemptDate) {
        this.attemptDate = attemptDate;
    }
    public Integer getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
    public String getSeverityLevel() {
        return severityLevel;
    }
    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    public List<AssessmentAnswer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<AssessmentAnswer> answers) {
        this.answers = answers;
    }
}