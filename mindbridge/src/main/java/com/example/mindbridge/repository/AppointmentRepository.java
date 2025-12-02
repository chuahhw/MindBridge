package com.example.mindbridge.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.User;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    // Find appointments by student
    List<Appointment> findByStudentIdOrderByDateDescTimeDesc(Long studentId);
    
    // Find appointments by counselor
    List<Appointment> findByCounselorIdOrderByDateDescTimeDesc(Long counselorId);
    
    // Find pending appointments for counselor
    List<Appointment> findByCounselorIdAndStatusOrderByDateAscTimeAsc(Long counselorId, String status);
    
    // Check if time slot is available
    boolean existsByCounselorAndDateAndTime(User counselor, LocalDate date, LocalTime time);
    
    // Find appointments for counselor on specific date
    @Query("SELECT a FROM Appointment a WHERE a.counselor = :counselor AND a.date = :date ORDER BY a.time ASC")
    List<Appointment> findByCounselorAndDate(@Param("counselor") User counselor, @Param("date") LocalDate date);

    // Find recent appointments across all users
    List<Appointment> findAllByOrderByDateDescTimeDesc();
}
