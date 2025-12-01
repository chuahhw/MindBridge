package com.example.mindbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mindbridge.model.LearningModule;

@Repository
public interface LearningModuleRepository extends JpaRepository<LearningModule, Long> {
    
    List<LearningModule> findByActiveTrueOrderByDisplayOrderAsc();
    
    long countByActiveTrue();

    // Find active modules by category
    List<LearningModule> findByCategoryAndActiveTrueOrderByDisplayOrderAsc(String category);

}