package com.example.mindbridge.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import com.example.mindbridge.model.Appointment;
import com.example.mindbridge.model.AssessmentAttempt;
import com.example.mindbridge.model.ForumReply;
import com.example.mindbridge.model.ForumThread;
import com.example.mindbridge.model.MoodEntry;
import com.example.mindbridge.model.QuestionSet;
import com.example.mindbridge.model.User;
import com.example.mindbridge.repository.AppointmentRepository;
import com.example.mindbridge.repository.AssessmentAttemptRepository;
import com.example.mindbridge.repository.ForumReplyRepository;
import com.example.mindbridge.repository.ForumThreadRepository;
import com.example.mindbridge.repository.MoodEntryRepository;
import com.example.mindbridge.repository.QuestionSetRepository;
import com.example.mindbridge.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminReportsController {

    private final UserRepository userRepository;
    private final AssessmentAttemptRepository assessmentAttemptRepository;
    private final MoodEntryRepository moodEntryRepository;
    private final ForumThreadRepository forumThreadRepository;
    private final ForumReplyRepository forumReplyRepository;
    private final AppointmentRepository appointmentRepository;
    private final QuestionSetRepository questionSetRepository;

    public AdminReportsController(
            UserRepository userRepository,
            AssessmentAttemptRepository assessmentAttemptRepository,
            MoodEntryRepository moodEntryRepository,
            ForumThreadRepository forumThreadRepository,
            ForumReplyRepository forumReplyRepository,
            AppointmentRepository appointmentRepository,
            QuestionSetRepository questionSetRepository) {
        this.userRepository = userRepository;
        this.assessmentAttemptRepository = assessmentAttemptRepository;
        this.moodEntryRepository = moodEntryRepository;
        this.forumThreadRepository = forumThreadRepository;
        this.forumReplyRepository = forumReplyRepository;
        this.appointmentRepository = appointmentRepository;
        this.questionSetRepository = questionSetRepository;
    }

    @GetMapping("/reports")
    public String adminReports(Model model) {
        // Overall Statistics
        long totalCompletedAssessments = assessmentAttemptRepository.count();
        long totalMoodEntries = moodEntryRepository.count();
        long totalForumThreads = forumThreadRepository.count();
        long totalForumReplies = forumReplyRepository.count();
        long totalForumPosts = totalForumThreads + totalForumReplies;
        long totalAppointments = appointmentRepository.count();
        long totalUsers = userRepository.findByRole("STUDENT").size() +
                         userRepository.findByRole("COUNSELOR").size() +
                         userRepository.findByRole("ADMIN").size();

        // Assessment Summary
        List<QuestionSet> questionSets = questionSetRepository.findAll();
        Map<String, Object> assessmentSummary = new HashMap<>();
        for (QuestionSet qs : questionSets) {
            long attempts = assessmentAttemptRepository.findByUser(null).stream()
                    .filter(attempt -> attempt.getQuestionSet().getId().equals(qs.getId()))
                    .count();
            assessmentSummary.put(qs.getName(), attempts);
        }

        model.addAttribute("totalCompletedAssessments", totalCompletedAssessments);
        model.addAttribute("totalMoodEntries", totalMoodEntries);
        model.addAttribute("totalForumPosts", totalForumPosts);
        model.addAttribute("totalForumThreads", totalForumThreads);
        model.addAttribute("totalForumReplies", totalForumReplies);
        model.addAttribute("totalAppointments", totalAppointments);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("assessmentSummary", assessmentSummary);

        return "admin-reports";
    }

    @GetMapping("/reports/export/{type}")
    public ResponseEntity<byte[]> exportPdf(@PathVariable String type) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("MindBridge System Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setMarginBottom(20));

        switch (type.toLowerCase()) {
            case "users":
                document.add(new Paragraph("User Data Report").setFontSize(16));
                addUserDataTable(document);
                break;
            case "assessments":
                document.add(new Paragraph("Assessment Data Report").setFontSize(16));
                addAssessmentDataTable(document);
                break;
            case "total":
                document.add(new Paragraph("Total Usage Report").setFontSize(16));
                addTotalUsageData(document);
                break;
        }

        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "mindbridge-" + type + "-report.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());
    }

    private void addUserDataTable(Document document) {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            document.add(new Paragraph("No users found."));
            return;
        }

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 3, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell("Name");
        table.addHeaderCell("Username");
        table.addHeaderCell("Email");
        table.addHeaderCell("Role");

        for (User user : users) {
            table.addCell(user.getFullName());
            table.addCell(user.getUsername());
            table.addCell(user.getEmail());
            table.addCell(user.getRole());
        }

        document.add(table);
    }

    private void addAssessmentDataTable(Document document) {
        List<AssessmentAttempt> attempts = assessmentAttemptRepository.findAll();

        if (attempts.isEmpty()) {
            document.add(new Paragraph("No assessment attempts found."));
            return;
        }

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 3, 2, 3}));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell("User");
        table.addHeaderCell("Assessment");
        table.addHeaderCell("Date");
        table.addHeaderCell("Score");
        table.addHeaderCell("Severity");

        for (AssessmentAttempt attempt : attempts) {
            table.addCell(attempt.getUser().getFullName());
            table.addCell(attempt.getQuestionSet().getName());
            table.addCell(attempt.getAttemptDate().toString());
            table.addCell(attempt.getTotalScore() != null ? attempt.getTotalScore().toString() : "N/A");
            table.addCell(attempt.getSeverityLevel() != null ? attempt.getSeverityLevel() : "N/A");
        }

        document.add(table);
    }

    private void addTotalUsageData(Document document) {
        // Overall statistics
        long totalUsers = userRepository.count();
        long totalCompletedAssessments = assessmentAttemptRepository.count();
        long totalMoodEntries = moodEntryRepository.count();
        long totalForumThreads = forumThreadRepository.count();
        long totalForumReplies = forumReplyRepository.count();
        long totalAppointments = appointmentRepository.count();

        document.add(new Paragraph("System Statistics"));
        document.add(new Paragraph("Total Users: " + totalUsers));
        document.add(new Paragraph("Total Completed Assessments: " + totalCompletedAssessments));
        document.add(new Paragraph("Total Mood Entries: " + totalMoodEntries));
        document.add(new Paragraph("Total Forum Threads: " + totalForumThreads));
        document.add(new Paragraph("Total Forum Replies: " + totalForumReplies));
        document.add(new Paragraph("Total Appointments: " + totalAppointments));

        // Appointments table
        document.add(new Paragraph("\nAppointments").setFontSize(14));
        List<Appointment> appointments = appointmentRepository.findAll();
        if (!appointments.isEmpty()) {
            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell("Student");
            table.addHeaderCell("Counselor");
            table.addHeaderCell("Date");
            table.addHeaderCell("Time");
            table.addHeaderCell("Status");

            for (Appointment apt : appointments) {
                table.addCell(apt.getStudent().getFullName());
                table.addCell(apt.getCounselor().getFullName());
                table.addCell(apt.getDate().toString());
                table.addCell(apt.getTime().toString());
                table.addCell(apt.getStatus());
            }

            document.add(table);
        }
    }
}
