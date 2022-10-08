package com.project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.spring.model.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
