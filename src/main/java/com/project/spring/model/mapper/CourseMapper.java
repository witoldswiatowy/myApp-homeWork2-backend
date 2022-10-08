package com.project.spring.model.mapper;

import com.project.spring.model.CourseEntity;
import com.project.spring.model.dto.CourseDto;
import com.project.spring.model.dto.CreateCourseRequest;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseDto toCourseDto (CourseEntity courseEntity){
        if (courseEntity == null){
            return null;
        }

        return CourseDto.builder()
                .id(courseEntity.getId())
                .createDate(courseEntity.getCreateDate())
                .updateDate(courseEntity.getUpdateDate())
                .version(courseEntity.getVersion())
                .name(courseEntity.getName())
                .startDate(courseEntity.getStartDate())
                .endDate(courseEntity.getEndDate())
                .build();
    }

    public static CourseEntity toCourseEntity (CourseDto courseDto){
        if (courseDto == null){
            return null;
        }

        CourseEntity course = new CourseEntity();
        course.setId(courseDto.getId());
        course.setCreateDate(courseDto.getCreateDate());
        course.setUpdateDate(courseDto.getUpdateDate());
        course.setVersion(courseDto.getVersion());
        course.setName(courseDto.getName());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());
        return course;
    }
    public static List<CourseDto> toCourseDtos(List<CourseEntity> courses) {
        return courses.stream()
                .map(CourseMapper::toCourseDto)
                .collect(Collectors.toList());
    }

    public static CourseEntity createRequestToCourseEntity(CreateCourseRequest request) {
        if (request == null){
            return null;
        }

        CourseEntity course = new CourseEntity();
        course.setName(request.getName());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        return course;
    }
}
