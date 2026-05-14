package com.example.finalproject100.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BagdauletAskarEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private BagdauletAskarStudent student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private BagdauletAskarCourse course;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "enrollment_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime enrollmentDate;
}
