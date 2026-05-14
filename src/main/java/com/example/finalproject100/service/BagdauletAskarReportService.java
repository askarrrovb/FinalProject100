package com.example.finalproject100.service;

import com.example.finalproject100.entity.BagdauletAskarGrade;
import com.example.finalproject100.entity.BagdauletAskarStudent;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.repository.BagdauletAskarGradeRepository;
import com.example.finalproject100.repository.BagdauletAskarStudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarReportService {

    private final BagdauletAskarStudentRepository studentRepository;
    private final BagdauletAskarGradeRepository gradeRepository;

    @Async("taskExecutor")
    @Transactional(readOnly = true)
    public CompletableFuture<String> generateStudentReport(Long studentId) {
        log.info("Generating report for student id: {}", studentId);
        BagdauletAskarStudent student = studentRepository.findById(studentId)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Student not found: " + studentId));

        List<BagdauletAskarGrade> grades = gradeRepository.findByStudentId(studentId);

        StringBuilder report = new StringBuilder();
        report.append("=== Student Report ===\n");
        report.append("Name: ").append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
        report.append("Email: ").append(student.getEmail()).append("\n");
        report.append("Total courses: ").append(grades.size()).append("\n");

        if (!grades.isEmpty()) {
            double avg = grades.stream().mapToDouble(BagdauletAskarGrade::getGrade).average().orElse(0.0);
            report.append("Average grade: ").append(String.format("%.2f", avg)).append("\n");
            report.append("Grades:\n");
            grades.forEach(g -> report.append("  - ")
                    .append(g.getCourse().getCourseName())
                    .append(": ").append(g.getGrade())
                    .append(" (").append(g.getLetterGrade()).append(")\n"));
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("Report generated for student: {}", studentId);
        return CompletableFuture.completedFuture(report.toString());
    }
}
