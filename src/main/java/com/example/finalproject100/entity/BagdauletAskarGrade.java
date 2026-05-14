package com.example.finalproject100.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BagdauletAskarGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private BagdauletAskarStudent student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private BagdauletAskarCourse course;

    @Column(name = "grade", nullable = false)
    private Double grade;

    @Column(name = "letter_grade")
    private String letterGrade;

    @Column(name = "semester")
    private String semester;

    @Column(name = "graded_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime gradedAt;
}
