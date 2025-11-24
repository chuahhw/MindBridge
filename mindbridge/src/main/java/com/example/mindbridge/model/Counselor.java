package com.example.mindbridge.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COUNSELOR")
public class Counselor extends User {

    public Counselor() {
        super();
        setRole("COUNSELOR");
    }

    public Counselor(String fullName, String username, String email, String password) {
        super(fullName, username, email, null, null, password, "COUNSELOR");
    }
}
