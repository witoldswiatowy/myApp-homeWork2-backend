package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssignmentDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;

    private boolean activeAssignment;
    private LocalDate assignmentToCourse;
    private LocalDate finishCourse;

    private StudentDto student;
    private ApplicationUserDto applicationUser;
    private CourseDto course;
}
