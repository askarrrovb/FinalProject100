package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarTeacherRequest;
import com.example.finalproject100.dto.response.BagdauletAskarTeacherResponse;
import com.example.finalproject100.service.BagdauletAskarTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class BagdauletAskarTeacherController {

    private final BagdauletAskarTeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<BagdauletAskarTeacherResponse>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarTeacherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BagdauletAskarTeacherResponse> create(@Valid @RequestBody BagdauletAskarTeacherRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BagdauletAskarTeacherResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BagdauletAskarTeacherRequest request) {
        return ResponseEntity.ok(teacherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
