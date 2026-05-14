package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarDepartmentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarDepartmentResponse;
import com.example.finalproject100.service.BagdauletAskarDepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class BagdauletAskarDepartmentController {

    private final BagdauletAskarDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<BagdauletAskarDepartmentResponse>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarDepartmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BagdauletAskarDepartmentResponse> create(@Valid @RequestBody BagdauletAskarDepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BagdauletAskarDepartmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BagdauletAskarDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
