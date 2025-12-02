package com.example.mindbridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByStudentId(String studentId);

    List<User> findByRole(String role);

    long countByRole(String role);
    
    // Fetch only counselors (subclass of User)
    @Query("SELECT c FROM Counselor c WHERE c.username = :username")
    Optional<Counselor> findCounselorByUsername(@Param("username") String username);
}


