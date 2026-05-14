package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BagdauletAskarCourseRepository extends JpaRepository<BagdauletAskarCourse, Long> {

    Optional<BagdauletAskarCourse> findByCourseCode(String courseCode);

    List<BagdauletAskarCourse> findByTeacherId(Long teacherId);

    boolean existsByCourseCode(String courseCode);
}
