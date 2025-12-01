package com.example.mindbridge.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mindbridge.model.LearningModule;
import com.example.mindbridge.model.QuizQuestion;
import com.example.mindbridge.model.Student;
import com.example.mindbridge.model.StudentProgress;
import com.example.mindbridge.repository.LearningModuleRepository;
import com.example.mindbridge.repository.QuizQuestionRepository;
import com.example.mindbridge.repository.StudentProgressRepository;

/**
 * LearningModuleService - method level transactions only
 */
@Service
public class LearningModuleService {

    private static final Logger logger = LoggerFactory.getLogger(LearningModuleService.class);

    private final LearningModuleRepository moduleRepository;
    private final StudentProgressRepository progressRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    @Autowired
    public LearningModuleService(LearningModuleRepository moduleRepository, StudentProgressRepository progressRepository, QuizQuestionRepository quizQuestionRepository) {
        this.moduleRepository = moduleRepository;
        this.progressRepository = progressRepository;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    // -----------------------
    // Read-only operations
    // -----------------------

    @Transactional(readOnly = true)
    public List<LearningModule> getAllModules() {
        try {
            List<LearningModule> modules = moduleRepository.findByActiveTrueOrderByDisplayOrderAsc();
            logger.info("Found {} active modules", modules.size());
            return modules;
        } catch (Exception e) {
            logger.error("Error fetching modules", e);
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public LearningModule getModuleById(Long id) {
        try {
            logger.info("Looking for module with ID: {}", id);
            Optional<LearningModule> moduleOpt = moduleRepository.findById(id);

            if (moduleOpt.isEmpty()) {
                logger.warn("Module not found with ID: {}", id);
                throw new RuntimeException("Module not found with id: " + id);
            }

            LearningModule module = moduleOpt.get();
            logger.info("Found module: {} (ID: {}, Active: {})",
                    module.getTitle(), module.getId(), module.getActive());

            if (Boolean.FALSE.equals(module.getActive())) {
                logger.warn("Module {} is inactive", id);
                throw new RuntimeException("Module is inactive: " + id);
            }

            return module;
        } catch (RuntimeException re) {
            // Rethrow runtime exceptions
            throw re;
        } catch (Exception e) {
            logger.error("Error fetching module with ID {}", id, e);
            throw new RuntimeException("Could not load module: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<StudentProgress> getStudentProgress(Student student) {
        if (student == null) {
            logger.warn("getStudentProgress called with null student");
            return Collections.emptyList();
        }
        try {
            List<StudentProgress> progress = progressRepository.findByStudent(student);
            logger.info("Found {} progress records for student {}", progress.size(), student.getId());
            return progress;
        } catch (Exception e) {
            logger.error("Error fetching student progress for student {}", student.getId(), e);
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public long getCompletedCount(Student student) {
        if (student == null) {
            logger.warn("getCompletedCount called with null student");
            return 0L;
        }
        try {
            long count = progressRepository.countByStudentAndCompletedTrue(student);
            logger.info("Student {} has completed {} modules", student.getId(), count);
            return count;
        } catch (Exception e) {
            logger.error("Error counting completed modules for student {}", student.getId(), e);
            return 0L;
        }
    }

    @Transactional(readOnly = true)
    public long getTotalCount() {
        try {
            long count = moduleRepository.countByActiveTrue();
            logger.info("Total active modules: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Error counting total modules", e);
            return 0L;
        }
    }

    @Transactional(readOnly = true)
public Optional<StudentProgress> getModuleProgress(Student student, Long moduleId) {
    if (student == null) {
        logger.warn("getModuleProgress called with null student");
        return Optional.empty();
    }
    try {
        // OPTION 1: Use the simpler method that works
        LearningModule module = getModuleById(moduleId);
        return progressRepository.findByStudentAndModule(student, module);
        
        // OPTION 2: Or comment out this method entirely if not used
        // return Optional.empty();
    } catch (Exception e) {
        logger.error("Error fetching progress for module {} and student {}", moduleId,
                student != null ? student.getId() : "null", e);
        return Optional.empty();
    }
}

    @Transactional(readOnly = true)
    public List<QuizQuestion> getQuizQuestions(Long moduleId) {
        try {
            List<QuizQuestion> questions = quizQuestionRepository.findByModuleIdOrderByIdAsc(moduleId);
            logger.info("Found {} questions for module {}", questions.size(), moduleId);
            return questions;
        } catch (Exception e) {
            logger.error("Error fetching questions for module {}", moduleId, e);
            return Collections.emptyList();
        }
    }

    // -----------------------
    // Write operations
    // -----------------------

    @Transactional
    public StudentProgress startModule(Student student, Long moduleId) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        try {
            LearningModule module = getModuleById(moduleId);

            Optional<StudentProgress> existingProgress = progressRepository.findByStudentAndModule(student, module);

            if (existingProgress.isPresent()) {
                StudentProgress progress = existingProgress.get();
                progress.setLastAccessed(java.time.LocalDateTime.now());
                StudentProgress saved = progressRepository.save(progress);
                logger.info("Updated last accessed for module {} for student {}", moduleId, student.getId());
                return saved;
            } else {
                StudentProgress progress = new StudentProgress(student, module);
                StudentProgress saved = progressRepository.save(progress);
                logger.info("Created new progress record for module {} for student {}", moduleId, student.getId());
                return saved;
            }
        } catch (Exception e) {
            logger.error("Error starting module {} for student {}", moduleId,
                    student != null ? student.getId() : "null", e);
            throw new RuntimeException("Could not start module: " + e.getMessage(), e);
        }
    }

    @Transactional
    public StudentProgress markQuizViewed(Student student, Long moduleId) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        try {
            LearningModule module = getModuleById(moduleId);
            StudentProgress progress = progressRepository.findByStudentAndModule(student, module)
                    .orElseGet(() -> new StudentProgress(student, module)); // Create if not exists

            if (Boolean.FALSE.equals(progress.getQuizViewed())) {
                progress.setQuizViewed(true);
                return progressRepository.save(progress);
            }
            return progress; // Already viewed, no change needed
        } catch (Exception e) {
            logger.error("Error marking quiz as viewed for module {} for student {}", moduleId, student.getId(), e);
            throw new RuntimeException("Could not mark quiz as viewed: " + e.getMessage(), e);
        }
    }

    @Transactional
    public StudentProgress completeModule(Student student, Long moduleId) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        try {
            LearningModule module = getModuleById(moduleId);
            StudentProgress progress = progressRepository.findByStudentAndModule(student, module)
                    .orElse(new StudentProgress(student, module));

            progress.setCompleted(true);
            progress.setCompletedAt(java.time.LocalDateTime.now());
            StudentProgress saved = progressRepository.save(progress);
            logger.info("Marked module {} as completed for student {}", moduleId, student.getId());
            return saved;
        } catch (Exception e) {
            logger.error("Error completing module {} for student {}", moduleId,
                    student != null ? student.getId() : "null", e);
            throw new RuntimeException("Could not complete module: " + e.getMessage(), e);
        }
    }

    @Transactional
    public LearningModule saveModule(LearningModule module) {
        try {
            LearningModule saved = moduleRepository.save(module);
            logger.info("Saved module: {}", saved.getTitle());
            return saved;
        } catch (Exception e) {
            logger.error("Error saving module", e);
            throw new RuntimeException("Could not save module: " + e.getMessage(), e);
        }
    }
}
