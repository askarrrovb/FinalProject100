package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.request.BagdauletAskarGradeRequest;
import com.example.finalproject100.dto.response.BagdauletAskarGradeResponse;
import com.example.finalproject100.entity.BagdauletAskarCourse;
import com.example.finalproject100.entity.BagdauletAskarGrade;
import com.example.finalproject100.entity.BagdauletAskarStudent;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarGradeMapper;
import com.example.finalproject100.repository.BagdauletAskarCourseRepository;
import com.example.finalproject100.repository.BagdauletAskarGradeRepository;
import com.example.finalproject100.repository.BagdauletAskarStudentRepository;
import com.example.finalproject100.service.BagdauletAskarGradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarGradeServiceImpl implements BagdauletAskarGradeService {

    private final BagdauletAskarGradeRepository gradeRepository;
    private final BagdauletAskarStudentRepository studentRepository;
    private final BagdauletAskarCourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BagdauletAskarGradeResponse> getAll() {
        return gradeRepository.findAll().stream()
                .map(BagdauletAskarGradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BagdauletAskarGradeResponse getById(Long id) {
        BagdauletAskarGrade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Grade not found with id: " + id));
        return BagdauletAskarGradeMapper.toResponse(grade);
    }

    @Override
    @Transactional
    public BagdauletAskarGradeResponse create(BagdauletAskarGradeRequest request) {
        log.info("Creating grade for student id: {}", request.getStudentId());
        BagdauletAskarStudent student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Student not found with id: " + request.getStudentId()));
        BagdauletAskarCourse course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Course not found with id: " + request.getCourseId()));

        BagdauletAskarGrade grade = new BagdauletAskarGrade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setGrade(request.getGrade());
        grade.setLetterGrade(calculateLetterGrade(request.getGrade()));
        grade.setSemester(request.getSemester());
        return BagdauletAskarGradeMapper.toResponse(gradeRepository.save(grade));
    }

    @Override
    @Transactional
    public BagdauletAskarGradeResponse update(Long id, BagdauletAskarGradeRequest request) {
        BagdauletAskarGrade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Grade not found with id: " + id));
        grade.setGrade(request.getGrade());
        grade.setLetterGrade(calculateLetterGrade(request.getGrade()));
        grade.setSemester(request.getSemester());
        return BagdauletAskarGradeMapper.toResponse(gradeRepository.save(grade));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BagdauletAskarGradeResponse> getByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
                .map(BagdauletAskarGradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    private String calculateLetterGrade(Double grade) {
        if (grade >= 90) return "A";
        if (grade >= 80) return "B";
        if (grade >= 70) return "C";
        if (grade >= 60) return "D";
        return "F";
    }
}
