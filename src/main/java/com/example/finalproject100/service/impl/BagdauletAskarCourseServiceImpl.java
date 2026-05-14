package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.request.BagdauletAskarCourseRequest;
import com.example.finalproject100.dto.response.BagdauletAskarCourseResponse;
import com.example.finalproject100.entity.BagdauletAskarCourse;
import com.example.finalproject100.entity.BagdauletAskarDepartment;
import com.example.finalproject100.entity.BagdauletAskarTeacher;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarCourseMapper;
import com.example.finalproject100.repository.BagdauletAskarCourseRepository;
import com.example.finalproject100.repository.BagdauletAskarDepartmentRepository;
import com.example.finalproject100.repository.BagdauletAskarTeacherRepository;
import com.example.finalproject100.service.BagdauletAskarCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarCourseServiceImpl implements BagdauletAskarCourseService {

    private final BagdauletAskarCourseRepository courseRepository;
    private final BagdauletAskarTeacherRepository teacherRepository;
    private final BagdauletAskarDepartmentRepository departmentRepository;

    @Override
    public List<BagdauletAskarCourseResponse> getAll() {
        log.info("Fetching all courses");
        return courseRepository.findAll()
                .stream()
                .map(BagdauletAskarCourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BagdauletAskarCourseResponse getById(Long id) {
        log.info("Fetching course with id: {}", id);
        BagdauletAskarCourse course = courseRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Course not found with id: " + id));
        return BagdauletAskarCourseMapper.toResponse(course);
    }

    @Override
    @Transactional
    public BagdauletAskarCourseResponse create(BagdauletAskarCourseRequest request) {
        log.info("Creating new course: {}", request.getCourseName());
        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new IllegalArgumentException("Course with code " + request.getCourseCode() + " already exists");
        }
        BagdauletAskarCourse course = new BagdauletAskarCourse();
        course.setCourseName(request.getCourseName());
        course.setCourseCode(request.getCourseCode());
        course.setCredits(request.getCredits());
        course.setDescription(request.getDescription());
        if (request.getTeacherId() != null) {
            BagdauletAskarTeacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Teacher not found with id: " + request.getTeacherId()));
            course.setTeacher(teacher);
        }
        if (request.getDepartmentId() != null) {
            BagdauletAskarDepartment department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));
            course.setDepartment(department);
        }
        return BagdauletAskarCourseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public BagdauletAskarCourseResponse update(Long id, BagdauletAskarCourseRequest request) {
        log.info("Updating course with id: {}", id);
        BagdauletAskarCourse course = courseRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Course not found with id: " + id));
        course.setCourseName(request.getCourseName());
        course.setCredits(request.getCredits());
        course.setDescription(request.getDescription());
        return BagdauletAskarCourseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting course with id: {}", id);
        if (!courseRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<BagdauletAskarCourseResponse> getByTeacherId(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(BagdauletAskarCourseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
