package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarStudentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarStudentResponse;
import com.example.finalproject100.service.BagdauletAskarReportService;
import com.example.finalproject100.service.BagdauletAskarStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Student management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class BagdauletAskarStudentController {

    private final BagdauletAskarStudentService studentService;
    private final BagdauletAskarReportService reportService;

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<BagdauletAskarStudentResponse>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/search")
    @Operation(summary = "Search students with pagination and sorting")
    public ResponseEntity<Page<BagdauletAskarStudentResponse>> getStudents(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studentService.getStudents(search, sortBy, sortDir, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarStudentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<BagdauletAskarStudentResponse> create(@Valid @RequestBody BagdauletAskarStudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<BagdauletAskarStudentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BagdauletAskarStudentRequest request) {
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<String> getReport(@PathVariable Long id) {
        try {
            String report = reportService.generateStudentReport(id).get();
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to generate report");
        }
    }
}
