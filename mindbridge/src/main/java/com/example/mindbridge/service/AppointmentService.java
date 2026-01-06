package com.example.mindbridge.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.User;


//Handles business rules
public interface AppointmentService {

    //Retrieve all appointment for specific student
    List<Appointment> getStudentAppointments(Long studentId);

    //Retrieve all appointment for specific counselor
    List<Appointment> getCounselorAppointments(Long counselorId);

    //Retrieve appointment that needs counselor action
    List<Appointment> getPendingAppointments(Long counselorId);

    //Book new appointment
    Appointment createAppointment(User student, User counselor, LocalDate date, LocalTime time, String type, String notes);

    //Change appointment status
    Appointment updateAppointmentStatus(Long appointmentId, String status);

    //Check time slot availability
    boolean isTimeSlotAvailable(User counselor, LocalDate date, LocalTime time);

    List<Appointment> getCounselorScheduleForDate(Long counselorId, LocalDate date);
}