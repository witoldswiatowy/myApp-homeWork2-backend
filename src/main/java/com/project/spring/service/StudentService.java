package com.project.spring.service;

import com.project.spring.model.dto.CreateStudentRequest;
import com.project.spring.model.dto.StudentDto;

import javax.persistence.EntityNotFoundException;

public interface StudentService {

    /**
     * Persists the passed student.
     * If the student has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param studentDto - params of student to create
     * @return created {@link StudentDto}
     */
    StudentDto createStudent(StudentDto studentDto);

    /**
     * Persists the passed student.
     * If the student has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param request - params of student to create
     * @return created {@link StudentDto}
     */
    StudentDto createStudent(CreateStudentRequest request);

    /**
     * Update the passed student.
     * If the student does not exist in DB, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param studentDto - params of student to create
     * @return updated {@link StudentDto}
     */
    StudentDto updateStudent(StudentDto studentDto);

    /**
     * Delete the passed student.
     * If the student does not exist in DB, then the implementation might throw an {@link EntityNotFoundException}.
     *
     * @param studentId - id of student to delete
     */
    void deleteStudentById(Long studentId);
}
