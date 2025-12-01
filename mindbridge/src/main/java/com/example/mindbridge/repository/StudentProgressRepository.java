package com.example.mindbridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.StudentProgress;

@Repository
public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long> {

    // Find all progress records for a student
    List<StudentProgress> findByStudent(Student student);

    // Find specific progress for a student and module
    Optional<StudentProgress> findByStudentAndModule(Student student, LearningModule module);

    // Count completed modules for a student
    long countByStudentAndCompletedTrue(Student student);

    // Check if a module is completed by a student
    boolean existsByStudentAndModuleAndCompletedTrue(Student student, LearningModule module);

    // Find all completed progress records for a student
    List<StudentProgress> findByStudentAndCompletedTrue(Student student);

    // Optimized query to fetch progress and module together
    @Query("SELECT p FROM StudentProgress p WHERE p.student = :student AND p.module.id = :moduleId")
    Optional<StudentProgress> findByStudentAndModuleId(
            @Param("student") Student student, @Param("moduleId") Long moduleId);
}