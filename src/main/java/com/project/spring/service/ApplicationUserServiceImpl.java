package com.project.spring.service;

import com.project.spring.model.ApplicationUserRole;
import com.project.spring.model.dto.ApplicationUserDTO;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.mapper.ApplicationUserMapper;
import com.project.spring.model.ApplicationUser;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Value("${application.default.newUserRole:ROLE_USER}")
    private String defaultNewUserRole;

    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserMapper applicationUserMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUserDTO addUser(CreateUserRequest request) {
        ApplicationUser newUser = applicationUserMapper.mapCreateUserRequestToApplicationUser(request);

        newUser.setPassword(passwordEncoder.encode(request.getPass()));
        newUser.setRoles(
                new HashSet<>(
                        Collections.singletonList(applicationUserRoleRepository
                                .findByName(defaultNewUserRole)
                                .orElseThrow(EntityNotFoundException::new))));

        return applicationUserMapper.mapApplicationUserToDTO(applicationUserRepository.save(newUser));
    }

    @Override
    public List<ApplicationUserDTO> listUsers() {
        return applicationUserRepository.findAll()
                .stream()
                .map(applicationUserMapper::mapApplicationUserToDTO)
                .collect(Collectors.toList());
    }
}
