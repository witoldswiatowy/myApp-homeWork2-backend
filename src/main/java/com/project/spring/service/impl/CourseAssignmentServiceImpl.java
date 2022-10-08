package com.project.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.spring.model.ApplicationUser;
import com.project.spring.model.CourseAssignmentEntity;
import com.project.spring.model.CourseEntity;
import com.project.spring.model.StudentEntity;
import com.project.spring.model.dto.CourseAssignmentDto;
import com.project.spring.model.dto.CourseAssignmentRequest;
import com.project.spring.model.mapper.ApplicationUserMapper;
import com.project.spring.model.mapper.CourseAssignmentMapper;
import com.project.spring.model.mapper.CourseMapper;
import com.project.spring.model.mapper.StudentMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.CourseAssignmentRepository;
import com.project.spring.repository.CourseRepository;
import com.project.spring.repository.StudentRepository;
import com.project.spring.service.CourseAssignmentService;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CourseAssignmentServiceImpl implements CourseAssignmentService {

    private final CourseAssignmentRepository courseAssignmentRepository;
    private final StudentRepository studentRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final CourseRepository courseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseAssignmentDto createCourseAssignment(CourseAssignmentDto courseAssignmentDto) {
        validateCorrectDtoForCrud(courseAssignmentDto);
        CourseAssignmentEntity courseAssignmentEntity = CourseAssignmentMapper.toCourseAssignmentEntity(courseAssignmentDto);
        if (courseAssignmentDto.getStudent().getId() != null) {
            StudentEntity studentEntity = StudentMapper.toStudentEntity(courseAssignmentDto.getStudent());
            StudentEntity managedEntity = studentRepository.save(studentEntity);
            courseAssignmentEntity.setStudent(managedEntity);
        }
/*        Long studentId = courseAssignmentDto.getStudent().getId();
        if (studentId != null) {
            StudentEntity studentEntity = studentRepository.findById(studentId).get();
//            StudentEntity studentEntity = studentRepository.findById(studentId)
//                    .orElseThrow(() -> new EntityNotFoundException("Impassible exception"));
            courseAssignmentEntity.setStudent(studentEntity);
        }*/
        if (courseAssignmentDto.getApplicationUser().getId() != null) {
            ApplicationUser applicationUserEntity = ApplicationUserMapper.toApplicationUser(courseAssignmentDto.getApplicationUser());
            ApplicationUser managedEntity = applicationUserRepository.save(applicationUserEntity);
            courseAssignmentEntity.setApplicationUser(managedEntity);
        }
        if (courseAssignmentDto.getCourse().getId() != null) {
            CourseEntity courseEntity = CourseMapper.toCourseEntity(courseAssignmentDto.getCourse());
            CourseEntity managedEntity = courseRepository.save(courseEntity);
            courseAssignmentEntity.setCourse(managedEntity);
        }
        CourseAssignmentEntity savedCourseAssignmentEntity = courseAssignmentRepository.save(courseAssignmentEntity);
        log.info("Create courseAssignment {}", savedCourseAssignmentEntity);
        return CourseAssignmentMapper.toCourseAssignmentDto(savedCourseAssignmentEntity);
    }

    @Override
    public CourseAssignmentDto createCourseAssignment(CourseAssignmentRequest courseAssignmentRequest) {
        validateCorrectRequestForCrud(courseAssignmentRequest);
        CourseAssignmentEntity courseAssignmentEntity = CourseAssignmentMapper.createRequestToCourseAssignmentEntity(courseAssignmentRequest);

        Long studentId = courseAssignmentRequest.getStudentId();
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Not found student with id: %s in database".formatted(studentId)));
        courseAssignmentEntity.setStudent(studentEntity);

        Long applicationUserId = courseAssignmentRequest.getApplicationUserId();
        ApplicationUser applicationUserEntity = applicationUserRepository.findById(applicationUserId)
                .orElseThrow(() -> new EntityNotFoundException("Not found application user with id: %s in database".formatted(applicationUserId)));
        courseAssignmentEntity.setApplicationUser(applicationUserEntity);

        Long courseId = courseAssignmentRequest.getCourseId();
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Not found course with id: %s in database".formatted(courseId)));
        courseAssignmentEntity.setCourse(courseEntity);

        CourseAssignmentEntity savedCourseAssignmentEntity = courseAssignmentRepository.save(courseAssignmentEntity);
        log.info("Create courseAssignment {}", savedCourseAssignmentEntity);
        return CourseAssignmentMapper.toCourseAssignmentDto(savedCourseAssignmentEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseAssignmentDto updateCourseAssignment(CourseAssignmentDto courseAssignmentWithChange) {
        validateCorrectDtoForCrud(courseAssignmentWithChange);
        CourseAssignmentEntity courseAssignmentEntity = courseAssignmentRepository.save(CourseAssignmentMapper.toCourseAssignmentEntity(courseAssignmentWithChange));
        log.info("Updating courseAssignment with id {}", courseAssignmentEntity.getId());
        return CourseAssignmentMapper.toCourseAssignmentDto(courseAssignmentEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCourseAssignmentById(Long courseAssignmentId) {
        if (courseAssignmentRepository.findById(courseAssignmentId).isPresent()) {
            log.info("Deleting courseAssignment with id {}", courseAssignmentId);
            courseAssignmentRepository.deleteById(courseAssignmentId);
            return;
        }
        log.error("CourseAssignment does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("Not found courseAssignment with id: %s in database".formatted(courseAssignmentId));
    }

    private void validateCorrectDtoForCrud(CourseAssignmentDto courseAssignmentDto) {
    }


    private void validateCorrectRequestForCrud(CourseAssignmentRequest request) {
        if (request == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
    }
}
