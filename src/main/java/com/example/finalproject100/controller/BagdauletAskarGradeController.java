package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarGradeRequest;
import com.example.finalproject100.dto.response.BagdauletAskarGradeResponse;
import com.example.finalproject100.service.BagdauletAskarGradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class BagdauletAskarGradeController {

    private final BagdauletAskarGradeService gradeService;

    @GetMapping
    public ResponseEntity<List<BagdauletAskarGradeResponse>> getAll() {
        return ResponseEntity.ok(gradeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarGradeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BagdauletAskarGradeResponse>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getByStudentId(studentId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<BagdauletAskarGradeResponse> create(@Valid @RequestBody BagdauletAskarGradeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<BagdauletAskarGradeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BagdauletAskarGradeRequest request) {
        return ResponseEntity.ok(gradeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gradeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
