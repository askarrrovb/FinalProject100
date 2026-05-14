package com.example.finalproject100.service.impl;

import com.example.finalproject100.dto.request.BagdauletAskarDepartmentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarDepartmentResponse;
import com.example.finalproject100.entity.BagdauletAskarDepartment;
import com.example.finalproject100.exception.BagdauletAskarResourceNotFoundException;
import com.example.finalproject100.mapper.BagdauletAskarDepartmentMapper;
import com.example.finalproject100.repository.BagdauletAskarDepartmentRepository;
import com.example.finalproject100.service.BagdauletAskarDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarDepartmentServiceImpl implements BagdauletAskarDepartmentService {

    private final BagdauletAskarDepartmentRepository departmentRepository;

    @Override
    public List<BagdauletAskarDepartmentResponse> getAll() {
        log.info("Fetching all departments");
        return departmentRepository.findAll()
                .stream()
                .map(BagdauletAskarDepartmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BagdauletAskarDepartmentResponse getById(Long id) {
        BagdauletAskarDepartment department = departmentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Department not found with id: " + id));
        return BagdauletAskarDepartmentMapper.toResponse(department);
    }

    @Override
    @Transactional
    public BagdauletAskarDepartmentResponse create(BagdauletAskarDepartmentRequest request) {
        log.info("Creating department: {}", request.getDepartmentName());
        if (departmentRepository.existsByDepartmentCode(request.getDepartmentCode())) {
            throw new IllegalArgumentException("Department code " + request.getDepartmentCode() + " already exists");
        }
        BagdauletAskarDepartment department = BagdauletAskarDepartmentMapper.toEntity(request);
        return BagdauletAskarDepartmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public BagdauletAskarDepartmentResponse update(Long id, BagdauletAskarDepartmentRequest request) {
        BagdauletAskarDepartment department = departmentRepository.findById(id)
                .orElseThrow(() -> new BagdauletAskarResourceNotFoundException("Department not found with id: " + id));
        department.setDepartmentName(request.getDepartmentName());
        department.setDescription(request.getDescription());
        department.setHead(request.getHead());
        return BagdauletAskarDepartmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new BagdauletAskarResourceNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
