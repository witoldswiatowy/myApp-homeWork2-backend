package com.project.spring.repository;

import com.project.spring.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    boolean existsByUsername(String username);
    Optional<ApplicationUser> findByUsername(String username);
}
