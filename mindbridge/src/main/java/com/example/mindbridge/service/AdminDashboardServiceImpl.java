package com.example.mindbridge.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.StudentProgress;
import com.example.mindbridge.repository.AppointmentRepository;
import com.example.mindbridge.repository.AssessmentAttemptRepository;
import com.example.mindbridge.repository.ForumThreadRepository;
import com.example.mindbridge.repository.StudentProgressRepository;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final AppointmentRepository appointmentRepository;
    private final StudentProgressRepository studentProgressRepository;
    private final AssessmentAttemptRepository assessmentAttemptRepository;
    private final ForumThreadRepository forumThreadRepository;

    public AdminDashboardServiceImpl(
            AppointmentRepository appointmentRepository,
            StudentProgressRepository studentProgressRepository,
            AssessmentAttemptRepository assessmentAttemptRepository,
            ForumThreadRepository forumThreadRepository) {
        this.appointmentRepository = appointmentRepository;
        this.studentProgressRepository = studentProgressRepository;
        this.assessmentAttemptRepository = assessmentAttemptRepository;
        this.forumThreadRepository = forumThreadRepository;
    }

    @Override
    public List<ActivityItem> getRecentActivities(int limit) {
        List<ActivityItem> activities = new ArrayList<>();

        // Get recent completed learning modules
        List<StudentProgress> recentCompletions = studentProgressRepository.findRecentCompletedModules()
            .stream().limit(limit).collect(Collectors.toList());
        for (StudentProgress progress : recentCompletions) {
            if (progress.getCompletedAt() != null) {
                String message = "Learning module completed: " + progress.getModule().getTitle();
                String timeAgo = formatTimeAgo(progress.getCompletedAt());
                activities.add(new ActivityItem(message, timeAgo));
            }
        }

        // Get recent appointments (using date as proxy for recency)
        List<Appointment> recentAppointments = appointmentRepository.findAllByOrderByDateDescTimeDesc()
            .stream().limit(limit).collect(Collectors.toList());
        for (Appointment appointment : recentAppointments) {
            if (appointment.getDate() != null) {
                String message = "New appointment booked for " +
                    appointment.getDate() + " at " + appointment.getTime();
                String timeAgo = formatDateTime(appointment.getDate().atTime(appointment.getTime()));
                activities.add(new ActivityItem(message, timeAgo));
            }
        }

        // Get recent assessment attempts
        List<AssessmentAttempt> recentAssessments = assessmentAttemptRepository.findAllByOrderByAttemptDateDesc()
            .stream().limit(limit).collect(Collectors.toList());
        for (AssessmentAttempt attempt : recentAssessments) {
            if (attempt.getAttemptDate() != null) {
                String message = "Assessment taken: " + attempt.getQuestionSet().getName();
                String timeAgo = formatTimeAgo(attempt.getAttemptDate());
                activities.add(new ActivityItem(message, timeAgo));
            }
        }

        // Get recent forum threads
        List<ForumThread> recentThreads = forumThreadRepository.findRecentForumThreads()
            .stream().limit(limit / 2).collect(Collectors.toList()); // Limit forum threads to prevent dominance
        for (ForumThread thread : recentThreads) {
            if (thread.getCreatedAt() != null) {
                String message = "New forum thread: " + thread.getTitle();
                String timeAgo = formatTimeAgo(thread.getCreatedAt());
                activities.add(new ActivityItem(message, timeAgo));
            }
        }

        // Sort all activities by their timestamp (most recent first)
        activities.sort(Comparator.comparing(this::parseTimeAgo).reversed());

        // Return top N activities
        return activities.stream().limit(limit).collect(Collectors.toList());
    }

    private String formatTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) return "Unknown time";

        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(dateTime, now);
        long hours = ChronoUnit.HOURS.between(dateTime, now);
        long days = ChronoUnit.DAYS.between(dateTime, now);

        if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours < 24) {
            return hours + " hours ago";
        } else {
            return days + " days ago";
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "Unknown time";
        return formatTimeAgo(dateTime);
    }

    // Parse time ago string back to a comparable value for sorting
    private long parseTimeAgo(ActivityItem item) {
        String timeStr = item.getTimeAgo();
        if (timeStr.contains("minutes ago")) {
            return Long.parseLong(timeStr.replace(" minutes ago", ""));
        } else if (timeStr.contains("hours ago")) {
            return Long.parseLong(timeStr.replace(" hours ago", "")) * 60;
        } else if (timeStr.contains("days ago")) {
            return Long.parseLong(timeStr.replace(" days ago", "")) * 24 * 60;
        }
        return 0;
    }
}
