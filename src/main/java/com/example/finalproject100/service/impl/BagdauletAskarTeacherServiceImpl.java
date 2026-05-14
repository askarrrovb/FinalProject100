package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.request.BagdauletAskarTeacherRequest;
import com.example.finalproject100.dto.response.BagdauletAskarTeacherResponse;
import com.example.finalproject100.entity.BagdauletAskarTeacher;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarTeacherMapper;
import com.example.finalproject100.repository.BagdauletAskarTeacherRepository;
import com.example.finalproject100.service.BagdauletAskarTeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarTeacherServiceImpl implements BagdauletAskarTeacherService {

    private final BagdauletAskarTeacherRepository teacherRepository;

    @Override
    public List<BagdauletAskarTeacherResponse> getAll() {
        log.info("Fetching all teachers");
        return teacherRepository.findAll()
                .stream()
                .map(BagdauletAskarTeacherMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BagdauletAskarTeacherResponse getById(Long id) {
        log.info("Fetching teacher with id: {}", id);
        BagdauletAskarTeacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Teacher not found with id: " + id));
        return BagdauletAskarTeacherMapper.toResponse(teacher);
    }

    @Override
    @Transactional
    public BagdauletAskarTeacherResponse create(BagdauletAskarTeacherRequest request) {
        log.info("Creating new teacher with email: {}", request.getEmail());
        if (teacherRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Teacher with email " + request.getEmail() + " already exists");
        }
        BagdauletAskarTeacher teacher = BagdauletAskarTeacherMapper.toEntity(request);
        BagdauletAskarTeacher saved = teacherRepository.save(teacher);
        log.info("Teacher created with id: {}", saved.getId());
        return BagdauletAskarTeacherMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BagdauletAskarTeacherResponse update(Long id, BagdauletAskarTeacherRequest request) {
        log.info("Updating teacher with id: {}", id);
        BagdauletAskarTeacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Teacher not found with id: " + id));
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
        teacher.setDepartment(request.getDepartment());
        teacher.setSalary(request.getSalary());
        BagdauletAskarTeacher updated = teacherRepository.save(teacher);
        return BagdauletAskarTeacherMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting teacher with id: {}", id);
        if (!teacherRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }
}
