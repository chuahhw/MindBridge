package com.example.mindbridge.model;

public class AssessmentResult {
    private int score;
    private int maxScore;
    private String severity;

    public AssessmentResult(int score, int maxScore, String severity) {
        this.score = score;
        this.maxScore = maxScore;
        this.severity = severity;
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getSeverity() {
        return severity;
    }
}