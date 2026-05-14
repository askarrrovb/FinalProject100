package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagdauletAskarTeacherRepository extends JpaRepository<BagdauletAskarTeacher, Long> {

    Optional<BagdauletAskarTeacher> findByEmail(String email);

    Optional<BagdauletAskarTeacher> findByTeacherId(String teacherId);

    boolean existsByEmail(String email);
}
