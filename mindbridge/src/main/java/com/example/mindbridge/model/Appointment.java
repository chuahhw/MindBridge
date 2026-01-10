package com.example.mindbridge.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Define structure of appointments data
@Entity  //JPA entity that will mapped to database table
@Table(name = "appointments")
public class Appointment {
    @Id  //Primary key
    //Autogenerate unique IDs (auto increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  //Many appointment can belong to one student
    //Creates foreign key in appointment table
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "counselor_id", nullable = false)
    private User counselor;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String type; // "IN_PERSON"

    @Column(nullable = false)
    private String status = "PENDING"; // "PENDING", "APPROVED", "DECLINED", "COMPLETED"

    @Column(length = 1000)
    private String notes;

    @Column(length = 1000)
    private String declineReason;

    // Constructors
    public Appointment() {}

    //Convenience constructor for creating new appointments
    public Appointment(User student, User counselor, LocalDate date, LocalTime time, String type, String notes) {
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public User getCounselor() { return counselor; }
    public void setCounselor(User counselor) { this.counselor = counselor; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getDeclineReason() { return declineReason; }
    public void setDeclineReason(String declineReason) { this.declineReason = declineReason; }
}