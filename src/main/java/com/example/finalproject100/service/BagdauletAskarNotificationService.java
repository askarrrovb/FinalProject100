package com.example.finalproject100.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BagdauletAskarNotificationService {

    @Async("taskExecutor")
    public void sendEnrollmentNotification(String email, String courseName) {
        log.info("Sending enrollment notification to: {} for course: {}", email, courseName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Enrollment notification sent to: {}", email);
    }

    @Async("taskExecutor")
    public void sendGradeNotification(String email, String courseName, Double grade) {
        log.info("Sending grade notification to: {} for course: {}", email, courseName);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Grade notification sent. Email: {}, Course: {}, Grade: {}", email, courseName, grade);
    }
}
