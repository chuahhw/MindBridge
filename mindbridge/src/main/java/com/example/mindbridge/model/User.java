package com.example.mindbridge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String studentId;
    private String department;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; 

    public User() {}

    public User(String fullName, String username, String email,
                String studentId, String department,
                String password, String role) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;        
        this.studentId = studentId;
        this.department = department;
        this.password = password;
        this.role = role;
    }

    // ----------------------------
    // Getters and Setters
    // ----------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // ----------------------------
    // Helper method for Spring Security
    // ----------------------------
    @Transient
    public String getUserType() {
        return role; 
    }
}
