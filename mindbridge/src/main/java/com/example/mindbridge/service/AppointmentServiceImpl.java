package com.example.mindbridge.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Appointment> getStudentAppointments(Long studentId) {
        return appointmentRepository.findByStudentIdOrderByDateDescTimeDesc(studentId);
    }

    @Override
    public List<Appointment> getCounselorAppointments(Long counselorId) {
        return appointmentRepository.findByCounselorIdOrderByDateDescTimeDesc(counselorId);
    }

    @Override
    public List<Appointment> getPendingAppointments(Long counselorId) {
        return appointmentRepository.findByCounselorIdAndStatusOrderByDateAscTimeAsc(counselorId, "PENDING");
    }

    @Override
    public Appointment createAppointment(User student, User counselor, LocalDate date, LocalTime time, String type, String notes) {
        Appointment appointment = new Appointment(student, counselor, date, time, type, notes);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointmentStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    @Override
    public boolean isTimeSlotAvailable(User counselor, LocalDate date, LocalTime time) {
        return !appointmentRepository.existsByCounselorAndDateAndTime(counselor, date, time);
    }

    @Override
    public List<Appointment> getCounselorScheduleForDate(Long counselorId, LocalDate date) {
        User counselor = userService.getUserById(counselorId);
        return appointmentRepository.findByCounselorAndDate(counselor, date);
    }
}