package com.example.mindbridge.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_question") // Match your actual table name
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Deprecated // Use questionText for clarity
    private String question;

    @Column(name = "question_text", columnDefinition = "TEXT")
    private String questionText;

    @ElementCollection
    @CollectionTable(name = "quiz_question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_text")
    private List<String> options;

    @Column(name = "correct_answer_index")
    private Integer correctAnswerIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private LearningModule module;

    // Constructors, getters, and setters...
    public QuizQuestion() {}

    public QuizQuestion(String question, List<String> options, Integer correctAnswerIndex, LearningModule module) {
        this.questionText = question;
        this.question = question; // Keep for backward compatibility if needed
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.module = module;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { 
        this.question = question; 
        if (this.questionText == null) {
            this.questionText = question;
        }
    }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    public Integer getCorrectAnswerIndex() { return correctAnswerIndex; }
    public void setCorrectAnswerIndex(Integer correctAnswerIndex) { this.correctAnswerIndex = correctAnswerIndex; }
    public LearningModule getModule() { return module; }
    public void setModule(LearningModule module) { this.module = module; }

    // Alias for getQuestion() for consistency with controller
    public String getQuestionText() {
        return this.questionText != null ? this.questionText : this.question;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // Alias for getCorrectAnswerIndex() for consistency with controller
    public Integer getCorrectAnswer() {
        return this.correctAnswerIndex;
    }
}