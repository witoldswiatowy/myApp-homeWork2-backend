package com.project.spring.service;

import com.project.spring.model.dto.ApplicationUserDTO;
import com.project.spring.model.dto.CreateUserRequest;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
public interface ApplicationUserService {
    ApplicationUserDTO addUser(CreateUserRequest request);
    List<ApplicationUserDTO> listUsers();
}
