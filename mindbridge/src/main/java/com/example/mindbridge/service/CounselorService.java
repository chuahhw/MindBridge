package com.example.mindbridge.service;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.repository.CounselorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    public Optional<Counselor> getCounselorByUsername(String username) {
        return counselorRepository.findByUsername(username);
    }
}