package com.project.spring.service;

import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
public interface ApplicationUserService {
    ApplicationUserDto addUser(CreateUserRequest request);
    List<ApplicationUserDto> listUsers();

    /**
     * Persists the passed applicationUser.
     * If the applicationUser has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param request - params of applicationUser to create
     * @return created {@link ApplicationUserDto}
     */
    ApplicationUserDto createUser(CreateUserRequest request);

    List<ApplicationUserDto> listUsers2();
}
