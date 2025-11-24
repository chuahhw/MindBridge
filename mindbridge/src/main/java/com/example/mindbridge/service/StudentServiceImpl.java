package com.example.mindbridge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mindbridge.model.Student;
import com.example.mindbridge.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public Student updateStudentProfile(Long id, Student updatedData) {
        Student student = getStudentById(id);

        student.setFullName(updatedData.getFullName());
        student.setEmail(updatedData.getEmail());
        student.setDepartment(updatedData.getDepartment());
        student.setStudentId(updatedData.getStudentId());

        return studentRepository.save(student);
    }

    @Override
    public void changePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        Student student = getStudentById(id);

        if (!passwordEncoder.matches(currentPassword, student.getPassword())) {
            throw new RuntimeException("Current password incorrect.");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New passwords do not match.");
        }

        student.setPassword(passwordEncoder.encode(newPassword));
        studentRepository.save(student);
    }
}
