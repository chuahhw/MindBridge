package com.example.mindbridge.repository;

import com.example.mindbridge.model.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    Optional<Counselor> findByUsername(String username);
}
