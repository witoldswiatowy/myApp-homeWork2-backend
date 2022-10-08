package com.project.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.spring.exception.ParamsIsEmptyException;
import com.project.spring.model.StudentEntity;
import com.project.spring.model.dto.CreateStudentRequest;
import com.project.spring.model.dto.StudentDto;
import com.project.spring.model.mapper.StudentMapper;
import com.project.spring.repository.StudentRepository;
import com.project.spring.service.StudentService;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        validateCorrectDtoForCrud(studentDto);
        StudentEntity studentEntity = StudentMapper.toStudentEntity(studentDto);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        log.info("Create student {}", savedStudentEntity);
        return StudentMapper.toStudentDto(savedStudentEntity);
    }

    @Override
    public StudentDto createStudent(CreateStudentRequest request) {
        validateCorrectRequestForCrud(request);
        StudentEntity studentEntity = StudentMapper.createRequestToStudentEntity(request);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        log.info("Create student {}", savedStudentEntity);
        return StudentMapper.toStudentDto(savedStudentEntity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto updateStudent(StudentDto studentWithChange) {
        validateCorrectDtoForCrud(studentWithChange);
        StudentEntity studentEntity = studentRepository.save(StudentMapper.toStudentEntity(studentWithChange));
        log.info("Updating student with id {}", studentEntity.getId());
        return StudentMapper.toStudentDto(studentEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStudentById(Long studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            log.info("Deleting student with id {}", studentId);
            studentRepository.deleteById(studentId);
            return;
        }
        log.error("Student does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("Not found student with id: %s in database".formatted(studentId));
    }

    private void validateCorrectDtoForCrud(StudentDto studentDto) {
        if (studentDto == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        if (studentDto.getFirstName() == null || studentDto.getFirstName().isBlank()){
            log.error("Can not create student without first name!");
            throw new ParamsIsEmptyException("Can not create student without first name!");
        }
        if (studentDto.getLastName() == null || studentDto.getLastName().isBlank()){
            log.error("Can not create student without last name!");
            throw new ParamsIsEmptyException("Can not create student without last name!");
        }
    }

    private void validateCorrectRequestForCrud(CreateStudentRequest request) {
        if (request == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        if (request.getFirstName() == null || request.getFirstName().isBlank()){
            log.error("Can not create student without first name!");
            throw new ParamsIsEmptyException("Can not create student without first name!");
        }
        if (request.getLastName() == null || request.getLastName().isBlank()){
            log.error("Can not create student without last name!");
            throw new ParamsIsEmptyException("Can not create student without last name!");
        }
    }
}
