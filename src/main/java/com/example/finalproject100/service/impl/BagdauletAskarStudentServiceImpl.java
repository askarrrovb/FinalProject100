package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.request.BagdauletAskarStudentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarStudentResponse;
import com.example.finalproject100.entity.BagdauletAskarStudent;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarStudentMapper;
import com.example.finalproject100.repository.BagdauletAskarStudentRepository;
import com.example.finalproject100.service.BagdauletAskarStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarStudentServiceImpl implements BagdauletAskarStudentService {

    private final BagdauletAskarStudentRepository studentRepository;

    @Override
    public List<BagdauletAskarStudentResponse> getAll() {
        log.info("Fetching all students");
        return studentRepository.findAll()
                .stream()
                .map(BagdauletAskarStudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BagdauletAskarStudentResponse getById(Long id) {
        log.info("Fetching student with id: {}", id);
        BagdauletAskarStudent student = studentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Student not found with id: " + id));
        return BagdauletAskarStudentMapper.toResponse(student);
    }

    @Override
    @Transactional
    public BagdauletAskarStudentResponse create(BagdauletAskarStudentRequest request) {
        log.info("Creating new student with email: {}", request.getEmail());
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Student with email " + request.getEmail() + " already exists");
        }
        BagdauletAskarStudent student = BagdauletAskarStudentMapper.toEntity(request);
        BagdauletAskarStudent saved = studentRepository.save(student);
        log.info("Student created with id: {}", saved.getId());
        return BagdauletAskarStudentMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BagdauletAskarStudentResponse update(Long id, BagdauletAskarStudentRequest request) {
        log.info("Updating student with id: {}", id);
        BagdauletAskarStudent student = studentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Student not found with id: " + id));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setEnrollmentDate(request.getEnrollmentDate());
        BagdauletAskarStudent updated = studentRepository.save(student);
        return BagdauletAskarStudentMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting student with id: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Page<BagdauletAskarStudentResponse> getStudents(String search, String sortBy, String sortDir, int page, int size) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BagdauletAskarStudent> studentPage;
        if (search != null && !search.isEmpty()) {
            studentPage = studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                    search, search, pageable);
        } else {
            studentPage = studentRepository.findAll(pageable);
        }
        return studentPage.map(BagdauletAskarStudentMapper::toResponse);
    }
}
