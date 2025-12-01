package com.example.mindbridge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mindbridge.model.Student;
import com.example.mindbridge.repository.StudentRepository;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public Student updateStudentProfile(Long id, Student updatedData) {
        Student student = getStudentById(id);

        student.setFullName(updatedData.getFullName());
        student.setEmail(updatedData.getEmail());
        student.setDepartment(updatedData.getDepartment());
        student.setStudentId(updatedData.getStudentId());

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void changePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        Student student = getStudentById(id);

        if (!passwordEncoder.matches(currentPassword, student.getPassword())) {
            throw new IllegalArgumentException("Current password incorrect.");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("New passwords do not match.");
        }

        student.setPassword(passwordEncoder.encode(newPassword));
        studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getLoggedInStudent(Authentication authentication) {
        // If authentication is null, try to get it from SecurityContextHolder
        if (authentication == null || !authentication.isAuthenticated()) {
            Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
            if (currentAuth == null || !currentAuth.isAuthenticated()) {
                throw new RuntimeException("User is not authenticated.");
            }
            authentication = currentAuth;
        }
        String username = authentication.getName();
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Authenticated username is empty.");
        }

        return getStudentByUsername(username)
                .orElseThrow(() -> new RuntimeException("Logged-in user is not a student or could not be found: " + username));
    }
}
