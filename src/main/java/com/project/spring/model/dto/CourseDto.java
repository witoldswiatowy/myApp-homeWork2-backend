package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    private Set<CourseAssignmentDto> courseAssignmentDtoSet;

}
