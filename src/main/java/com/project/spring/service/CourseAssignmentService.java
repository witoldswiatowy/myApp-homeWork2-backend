package com.project.spring.service;

import com.project.spring.model.dto.CourseAssignmentDto;
import com.project.spring.model.dto.CourseAssignmentRequest;

import javax.persistence.EntityNotFoundException;

public interface CourseAssignmentService {

    /**
     * Persists the passed courseAssignment.
     * If the courseAssignment has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param courseAssignmentDto - params of courseAssignment to create
     * @return created {@link CourseAssignmentDto}
     */
    CourseAssignmentDto createCourseAssignment(CourseAssignmentDto courseAssignmentDto);

    /**
     * Persists the passed courseAssignment.
     * If the courseAssignment has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param courseAssignmentRequest - params of courseAssignment to create
     * @return created {@link CourseAssignmentDto}
     */
    CourseAssignmentDto createCourseAssignment(CourseAssignmentRequest courseAssignmentRequest);

    /**
     * Update the passed courseAssignment.
     * If the courseAssignment does not exist in DB, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param courseAssignmentDto - params of courseAssignment to create
     * @return updated {@link CourseAssignmentDto}
     */
    CourseAssignmentDto updateCourseAssignment(CourseAssignmentDto courseAssignmentDto);

    /**
     * Delete the passed courseAssignment.
     * If the courseAssignment does not exist in DB, then the implementation might throw an {@link EntityNotFoundException}.
     *
     * @param courseAssignmentId - id of courseAssignment to delete
     */
    void deleteCourseAssignmentById(Long courseAssignmentId);
}
