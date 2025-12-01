package com.example.mindbridge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment_answer")
public class AssessmentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private AssessmentAttempt attempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_option_id", nullable = false)
    private AnswerOption answerOption;

    @Column(nullable = false)
    private int score;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public AssessmentAttempt getAttempt() {
        return attempt;
    }
    public void setAttempt(AssessmentAttempt attempt) {
        this.attempt = attempt;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public AnswerOption getAnswerOption() {
        return answerOption;
    }
    public void setAnswerOption(AnswerOption answerOption) {
        this.answerOption = answerOption;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}