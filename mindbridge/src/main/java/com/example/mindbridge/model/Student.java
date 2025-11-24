package com.example.mindbridge.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    public Student() {
        super();
        setRole("STUDENT");
    }

    public Student(String fullName, String username, String email,
                   String studentId, String department, String password, String role) {
        super(fullName, username, email, studentId, department, password, role);
    }

}
