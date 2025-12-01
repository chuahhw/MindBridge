package com.example.mindbridge.service;

import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.Student;
import java.util.List;
import java.util.Map;

public interface AssessmentService {
    List<AssessmentAttempt> getPreviousAttempts(Student student);

    QuestionSet getQuestionSetByName(String name);

    AssessmentAttempt saveAssessment(Student student, String setName, Map<String, String> answers);

    AssessmentAttempt getAssessmentAttemptById(Integer attemptId);

    int getMaxScoreForSet(String setName);
}
