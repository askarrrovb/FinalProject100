package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagdauletAskarGradeRepository extends JpaRepository<BagdauletAskarGrade, Long> {

    List<BagdauletAskarGrade> findByStudentId(Long studentId);

    List<BagdauletAskarGrade> findByCourseId(Long courseId);

    List<BagdauletAskarGrade> findByStudentIdAndSemester(Long studentId, String semester);
}
