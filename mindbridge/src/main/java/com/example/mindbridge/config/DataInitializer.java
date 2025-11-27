package com.example.mindbridge.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if counselors already exist
        if (userRepository.findByRole("ROLE_COUNSELOR").isEmpty()) {
            System.out.println("🔧 Creating sample counselors...");
            
            // Counselor 1
            Counselor counselor1 = new Counselor();
            counselor1.setFullName("Dr. Michael Chen");
            counselor1.setUsername("michael.chen");
            counselor1.setEmail("michael.chen@university.edu");
            counselor1.setPassword(passwordEncoder.encode("password123"));
            counselor1.setSpecialty("Clinical Psychology");
            counselor1.setAvailability("Mon, Wed, Fri");
            userRepository.save(counselor1);
            
            // Counselor 2
            Counselor counselor2 = new Counselor();
            counselor2.setFullName("Dr. Sarah Williams");
            counselor2.setUsername("sarah.williams");
            counselor2.setEmail("sarah.williams@university.edu");
            counselor2.setPassword(passwordEncoder.encode("password123"));
            counselor2.setSpecialty("Anxiety & Depression");
            counselor2.setAvailability("Tue, Thu, Fri");
            userRepository.save(counselor2);
            
            // Counselor 3
            Counselor counselor3 = new Counselor();
            counselor3.setFullName("Dr. James Rodriguez");
            counselor3.setUsername("james.rodriguez");
            counselor3.setEmail("james.rodriguez@university.edu");
            counselor3.setPassword(passwordEncoder.encode("password123"));
            counselor3.setSpecialty("Student Counseling");
            counselor3.setAvailability("Mon, Tue, Thu");
            userRepository.save(counselor3);
            
            System.out.println("✅ 3 sample counselors created successfully!");
        } else {
            System.out.println("ℹ️  Counselors already exist in database (count: " + 
                userRepository.findByRole("ROLE_COUNSELOR").size() + ")");
        }
    }
}