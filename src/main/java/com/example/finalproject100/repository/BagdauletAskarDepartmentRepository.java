package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagdauletAskarDepartmentRepository extends JpaRepository<BagdauletAskarDepartment, Long> {

    Optional<BagdauletAskarDepartment> findByDepartmentCode(String departmentCode);

    boolean existsByDepartmentCode(String departmentCode);
}
