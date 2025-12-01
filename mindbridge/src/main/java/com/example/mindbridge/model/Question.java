package com.example.mindbridge.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_set_id", nullable = false)
    private QuestionSet questionSet;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "question_order", nullable = false)
    private int questionOrder;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AnswerOption> answerOptions;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public QuestionSet getQuestionSet() {
        return questionSet;
    }
    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public int getQuestionOrder() {
        return questionOrder;
    }
    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }
    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }
    public void setAnswerOptions(List<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
}