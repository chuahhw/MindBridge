package com.example.mindbridge.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COUNSELOR")
public class Counselor extends User {

    private String specialty;
    private String availability;

    public Counselor() {
        super();
        setRole("ROLE_COUNSELOR");
    }

    public Counselor(String fullName, String username, String email, String password) {
        super(fullName, username, email, null, null, password, "ROLE_COUNSELOR");
    }

    public Counselor(String fullName, String username, String email, String password, 
                     String specialty, String availability) {
        super(fullName, username, email, null, null, password, "ROLE_COUNSELOR");
        this.specialty = specialty;
        this.availability = availability;
    }

    // Getters and Setters
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}