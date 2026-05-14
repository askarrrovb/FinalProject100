package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagdauletAskarStudentRepository extends JpaRepository<BagdauletAskarStudent, Long> {

    Optional<BagdauletAskarStudent> findByEmail(String email);

    Optional<BagdauletAskarStudent> findByStudentId(String studentId);

    Page<BagdauletAskarStudent> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByStudentId(String studentId);
}
