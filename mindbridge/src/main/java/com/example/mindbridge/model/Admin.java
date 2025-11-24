package com.example.mindbridge.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    public Admin() {
        super();
        setRole("ADMIN");
    }

    public Admin(String fullName, String username, String email, String password) {
        super(fullName, username, email, null, null, password, "ADMIN");
    }
}
