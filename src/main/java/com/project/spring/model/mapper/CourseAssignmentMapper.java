package com.project.spring.model.mapper;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.CourseAssignmentEntity;
import com.project.spring.model.CourseEntity;
import com.project.spring.model.StudentEntity;
import com.project.spring.model.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class CourseAssignmentMapper {

    private CourseAssignmentMapper() {}

    public static CourseAssignmentDto toCourseAssignmentDto (CourseAssignmentEntity courseAssignmentEntity){
        if (courseAssignmentEntity == null){
            return null;
        }

        StudentDto studentDto = StudentMapper.toStudentDto(courseAssignmentEntity.getStudent());
        ApplicationUserDto applicationUserDto = ApplicationUserMapper2.toApplicationUserDto(courseAssignmentEntity.getApplicationUser());
        CourseDto courseDto = CourseMapper.toCourseDto(courseAssignmentEntity.getCourse());

        return CourseAssignmentDto.builder()
                .id(courseAssignmentEntity.getId())
                .createDate(courseAssignmentEntity.getCreateDate())
                .updateDate(courseAssignmentEntity.getUpdateDate())
                .version(courseAssignmentEntity.getVersion())
                .activeAssignment(courseAssignmentEntity.isActiveAssignment())
                .assignmentToCourse(courseAssignmentEntity.getAssignmentToCourse())
                .finishCourse(courseAssignmentEntity.getFinishCourse())
                .student(studentDto)
                .applicationUser(applicationUserDto)
                .course(courseDto)
                .build();
    }

    public static CourseAssignmentEntity toCourseAssignmentEntity (CourseAssignmentDto courseAssignmentDto){
        if (courseAssignmentDto == null){
            return null;
        }

        StudentEntity studentEntity = StudentMapper.toStudentEntity(courseAssignmentDto.getStudent());
        ApplicationUser applicationUser = ApplicationUserMapper2.toApplicationUser(courseAssignmentDto.getApplicationUser());
        CourseEntity courseEntity = CourseMapper.toCourseEntity(courseAssignmentDto.getCourse());

        CourseAssignmentEntity courseAssignment = new CourseAssignmentEntity();
        courseAssignment.setId(courseAssignmentDto.getId());
        courseAssignment.setCreateDate(courseAssignmentDto.getCreateDate());
        courseAssignment.setUpdateDate(courseAssignmentDto.getUpdateDate());
        courseAssignment.setVersion(courseAssignmentDto.getVersion());
        courseAssignment.setActiveAssignment(courseAssignmentDto.isActiveAssignment());
        courseAssignment.setAssignmentToCourse(courseAssignmentDto.getAssignmentToCourse());
        courseAssignment.setFinishCourse(courseAssignmentDto.getFinishCourse());
        courseAssignment.setStudent(studentEntity);
        courseAssignment.setApplicationUser(applicationUser);
        courseAssignment.setCourse(courseEntity);
        return courseAssignment;
    }
    public static List<CourseAssignmentDto> toCourseAssignmentDtos(List<CourseAssignmentEntity> courseAssignments) {
        return courseAssignments.stream()
                .map(CourseAssignmentMapper::toCourseAssignmentDto)
                .collect(Collectors.toList());
    }

    public static CourseAssignmentEntity createRequestToCourseAssignmentEntity (CourseAssignmentRequest courseAssignmentRequest){
        if (courseAssignmentRequest == null){
            return null;
        }

        CourseAssignmentEntity courseAssignment = new CourseAssignmentEntity();
        courseAssignment.setActiveAssignment(courseAssignmentRequest.isActiveAssignment());
        courseAssignment.setAssignmentToCourse(courseAssignmentRequest.getAssignmentToCourse());
        courseAssignment.setFinishCourse(courseAssignmentRequest.getFinishCourse());
        return courseAssignment;
    }
}
