package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagdauletAskarEnrollmentRepository extends JpaRepository<BagdauletAskarEnrollment, Long> {

    List<BagdauletAskarEnrollment> findByStudentId(Long studentId);

    List<BagdauletAskarEnrollment> findByCourseId(Long courseId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
