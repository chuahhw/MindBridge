package com.example.mindbridge.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.User;

public interface AppointmentService {
    List<Appointment> getStudentAppointments(Long studentId);
    List<Appointment> getCounselorAppointments(Long counselorId);
    List<Appointment> getPendingAppointments(Long counselorId);
    Appointment createAppointment(User student, User counselor, LocalDate date, LocalTime time, String type, String notes);
    Appointment updateAppointmentStatus(Long appointmentId, String status);
    boolean isTimeSlotAvailable(User counselor, LocalDate date, LocalTime time);
    List<Appointment> getCounselorScheduleForDate(Long counselorId, LocalDate date);
}