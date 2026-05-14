package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.response.BagdauletAskarCourseResponse;
import com.example.finalproject100.entity.BagdauletAskarCourse;

public class BagdauletAskarCourseMapper {

    public static BagdauletAskarCourseResponse toResponse(BagdauletAskarCourse course) {
        BagdauletAskarCourseResponse response = new BagdauletAskarCourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setCourseCode(course.getCourseCode());
        response.setCredits(course.getCredits());
        response.setDescription(course.getDescription());
        response.setCreatedAt(course.getCreatedAt());
        if (course.getTeacher() != null) {
            response.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        }
        if (course.getDepartment() != null) {
            response.setDepartmentName(course.getDepartment().getDepartmentName());
        }
        return response;
    }
}
