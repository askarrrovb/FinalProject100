package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.response.BagdauletAskarEnrollmentResponse;
import com.example.finalproject100.entity.BagdauletAskarCourse;
import com.example.finalproject100.entity.BagdauletAskarEnrollment;
import com.example.finalproject100.entity.BagdauletAskarStudent;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarEnrollmentMapper;
import com.example.finalproject100.repository.BagdauletAskarCourseRepository;
import com.example.finalproject100.repository.BagdauletAskarEnrollmentRepository;
import com.example.finalproject100.repository.BagdauletAskarStudentRepository;
import com.example.finalproject100.service.BagdauletAskarEnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarEnrollmentServiceImpl implements BagdauletAskarEnrollmentService {

    private final BagdauletAskarEnrollmentRepository enrollmentRepository;
    private final BagdauletAskarStudentRepository studentRepository;
    private final BagdauletAskarCourseRepository courseRepository;

    @Override
    public List<BagdauletAskarEnrollmentResponse> getAll() {
        return enrollmentRepository.findAll().stream()
                .map(BagdauletAskarEnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BagdauletAskarEnrollmentResponse getById(Long id) {
        BagdauletAskarEnrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Enrollment not found with id: " + id));
        return BagdauletAskarEnrollmentMapper.toResponse(enrollment);
    }

    @Override
    @Transactional
    public BagdauletAskarEnrollmentResponse enrollStudent(Long studentId, Long courseId) {
        log.info("Enrolling student {} in course {}", studentId, courseId);
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }
        BagdauletAskarStudent student = studentRepository.findById(studentId)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Student not found with id: " + studentId));
        BagdauletAskarCourse course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Course not found with id: " + courseId));

        BagdauletAskarEnrollment enrollment = new BagdauletAskarEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus("ACTIVE");
        return BagdauletAskarEnrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional
    public BagdauletAskarEnrollmentResponse updateStatus(Long id, String status) {
        BagdauletAskarEnrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Enrollment not found with id: " + id));
        enrollment.setStatus(status);
        return BagdauletAskarEnrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<BagdauletAskarEnrollmentResponse> getByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(BagdauletAskarEnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
