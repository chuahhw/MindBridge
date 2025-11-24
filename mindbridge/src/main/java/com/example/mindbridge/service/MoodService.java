package com.example.mindbridge.service;

import java.util.List;

import com.example.mindbridge.model.MoodEntry;
import com.example.mindbridge.model.Student;

public interface MoodService {

    /** Add a new mood entry */
    void addMood(Student student, String moodLabel, String note);

    /** Get all moods for a student (newest first) */
    List<MoodEntry> getMoodsForStudent(Student student);

    /** Get the latest mood entry */
    MoodEntry getLatestMood(Student student);

    /** Calculate consecutive daily streak */
    int getCurrentStreak(Student student);

    /** Calculate 7-day average mood (converted to numeric internally) */
    double get7DayAverage(Student student);

    /** Get total number of mood entries */
    int getTotalEntries(Student student);
}
