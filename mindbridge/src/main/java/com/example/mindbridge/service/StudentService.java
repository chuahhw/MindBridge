package com.example.mindbridge.service;

import java.util.Optional;

import com.example.mindbridge.model.Student;

public interface StudentService {

    Student getStudentById(Long id);

    Optional<Student> getStudentByUsername(String username); 

    Student updateStudentProfile(Long id, Student updatedData);

    void changePassword(Long id, String currentPassword, String newPassword, String confirmPassword);
}
