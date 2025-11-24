package com.example.mindbridge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.MoodEntry;
import com.example.mindbridge.model.Student;

@Repository
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {

    List<MoodEntry> findByStudentOrderByEntryDateDesc(Student student);

    List<MoodEntry> findByStudentAndEntryDateBetween(
            Student student,
            LocalDate startDate,
            LocalDate endDate
    );

    @Query("SELECT m.mood, COUNT(m) " +
           "FROM MoodEntry m " +
           "WHERE m.student = :student " +
           "GROUP BY m.mood")
    List<Object[]> findMoodStatsByStudent(@Param("student") Student student);
}
