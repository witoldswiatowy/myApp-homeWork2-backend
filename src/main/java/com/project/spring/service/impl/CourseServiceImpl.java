package com.project.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.spring.exception.ParamsIsEmptyException;
import com.project.spring.exception.ParamsIsIncorrectException;
import com.project.spring.model.CourseEntity;
import com.project.spring.model.dto.CourseDto;
import com.project.spring.model.dto.CreateCourseRequest;
import com.project.spring.model.mapper.CourseMapper;
import com.project.spring.repository.CourseRepository;
import com.project.spring.service.CourseService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        validateCorrectDtoForCrud(courseDto);
        CourseEntity courseEntity = CourseMapper.toCourseEntity(courseDto);
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        log.info("Create course {}", savedCourseEntity);
        return CourseMapper.toCourseDto(savedCourseEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDto createCourse(CreateCourseRequest request) {
        validateCorrectRequestForCrud(request);
        CourseEntity courseEntity = CourseMapper.createRequestToCourseEntity(request);
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        log.info("Create course {}", savedCourseEntity);
        return CourseMapper.toCourseDto(savedCourseEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDto updateCourse(CourseDto courseWithChange) {
        validateCorrectDtoForCrud(courseWithChange);
        CourseEntity courseEntity = courseRepository.save(CourseMapper.toCourseEntity(courseWithChange));
        log.info("Updating course with id {}", courseEntity.getId());
        return CourseMapper.toCourseDto(courseEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCourseById(Long courseId) {
        if (courseRepository.findById(courseId).isPresent()) {
            log.info("Deleting course with id {}", courseId);
            courseRepository.deleteById(courseId);
            return;
        }
        log.error("Course does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("Not found course with id: %s in database".formatted(courseId));
    }

    private void validateCorrectDtoForCrud(CourseDto courseDto) {
        if (courseDto == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        validateForCrud(courseDto.getName(), courseDto.getStartDate(), courseDto.getEndDate());
    }

    private void validateCorrectRequestForCrud(CreateCourseRequest request) {
        if (request == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        validateForCrud(request.getName(), request.getStartDate(), request.getEndDate());
    }

    private void validateForCrud(String name, LocalDate startDate, LocalDate endDate) {
        if (name == null || name.isBlank()){
            log.error("Can not create course without name!");
            throw new ParamsIsEmptyException("Can not create course without name!");
        }
        if (startDate == null || endDate == null) {
            log.error("Course need declared date start and end!");
            throw new IllegalArgumentException();
        }
        if (!startDate.isBefore(endDate)) {
            log.error("\"Start date\" must be before \"end date\"!");
            throw new ParamsIsIncorrectException("\"Start date\" must be before \"end date\"!");
        }
    }
}
