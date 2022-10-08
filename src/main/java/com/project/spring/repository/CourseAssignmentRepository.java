package com.project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.spring.model.CourseAssignmentEntity;

@Repository
public interface CourseAssignmentRepository extends JpaRepository<CourseAssignmentEntity, Long> {
}
