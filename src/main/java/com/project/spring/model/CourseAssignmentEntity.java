package com.project.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course_assignment")
public class CourseAssignmentEntity extends BaseEntity{

    private boolean activeAssignment;
    private LocalDate assignmentToCourse;
    private LocalDate finishCourse;
    @ManyToOne
    private StudentEntity student;
    @ManyToOne
    private ApplicationUser applicationUser;
    @ManyToOne
    private CourseEntity course;
}
