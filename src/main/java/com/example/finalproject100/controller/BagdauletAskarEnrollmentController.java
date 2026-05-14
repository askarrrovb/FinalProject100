package com.example.finalproject100.controller;

import com.example.finalproject100.dto.response.BagdauletAskarEnrollmentResponse;
import com.example.finalproject100.service.BagdauletAskarEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class BagdauletAskarEnrollmentController {

    private final BagdauletAskarEnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<BagdauletAskarEnrollmentResponse>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarEnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BagdauletAskarEnrollmentResponse>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getByStudentId(studentId));
    }

    @PostMapping("/enroll")
    public ResponseEntity<BagdauletAskarEnrollmentResponse> enroll(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudent(studentId, courseId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BagdauletAskarEnrollmentResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(enrollmentService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
