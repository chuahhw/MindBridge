package com.example.mindbridge.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mindbridge.model.MoodEntry;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.repository.MoodEntryRepository;

@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodEntryRepository moodRepository;

    /** ============================
     *  ADD MOOD ENTRY
     * ============================ */
    @Override
    public void addMood(Student student, String moodLabel, String note) {
        MoodEntry entry = new MoodEntry();
        entry.setStudent(student);
        entry.setMood(moodLabel);
        entry.setNotes(note);
        entry.setEntryDate(LocalDate.now());

        moodRepository.save(entry);
    }

    /** ============================
     *  GET ALL MOODS (latest first)
     * ============================ */
    @Override
    public List<MoodEntry> getMoodsForStudent(Student student) {
        return moodRepository.findByStudentOrderByEntryDateDesc(student);
    }

    /** ============================
     *  GET LATEST MOOD
     * ============================ */
    @Override
    public MoodEntry getLatestMood(Student student) {
        List<MoodEntry> list = moodRepository.findByStudentOrderByEntryDateDesc(student);
        return list.isEmpty() ? null : list.get(0);
    }

    /** ============================
     *  CALCULATE CURRENT STREAK
     * ============================ */
    @Override
    public int getCurrentStreak(Student student) {
        List<MoodEntry> moods = getMoodsForStudent(student);

        if (moods.isEmpty()) return 0;

        int streak = 0;
        LocalDate today = LocalDate.now();

        for (MoodEntry mood : moods) {
            LocalDate moodDate = mood.getEntryDate();
            LocalDate expectedDate = today.minusDays(streak);

            if (moodDate.isEqual(expectedDate)) {
                streak++;
            } else {
                break;
            }
        }

        return streak;
    }

    /** ============================
     *  CALCULATE 7-DAY AVERAGE
     * ============================ */
    @Override
    public double get7DayAverage(Student student) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(6);

        List<MoodEntry> weekMoods = moodRepository
                .findByStudentAndEntryDateBetween(student, startDate, today);

        if (weekMoods.isEmpty()) return 0;

        return weekMoods.stream()
                .mapToInt(m -> convertMoodLabelToValue(m.getMood()))
                .average()
                .orElse(0);
    }

    /** ============================
     *  TOTAL ENTRIES
     * ============================ */
    @Override
    public int getTotalEntries(Student student) {
        return moodRepository.findByStudentOrderByEntryDateDesc(student).size();
    }

    /** ============================
     *  Helper: Mood label → numeric value
     * ============================ */
    private int convertMoodLabelToValue(String label) {
        return switch (label) {
            case "Great" -> 5;
            case "Good" -> 4;
            case "Okay" -> 3;
            case "Not Great" -> 2;
            case "Bad" -> 1;
            case "Awful" -> 0;
            default -> 0;
        };
    }
}
