package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.response.BagdauletAskarGradeResponse;
import com.example.finalproject100.entity.BagdauletAskarGrade;

public class BagdauletAskarGradeMapper {

    public static BagdauletAskarGradeResponse toResponse(BagdauletAskarGrade grade) {
        BagdauletAskarGradeResponse response = new BagdauletAskarGradeResponse();
        response.setId(grade.getId());
        response.setGrade(grade.getGrade());
        response.setLetterGrade(grade.getLetterGrade());
        response.setSemester(grade.getSemester());
        response.setGradedAt(grade.getGradedAt());
        if (grade.getStudent() != null) {
            response.setStudentName(grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
        }
        if (grade.getCourse() != null) {
            response.setCourseName(grade.getCourse().getCourseName());
        }
        return response;
    }
}
