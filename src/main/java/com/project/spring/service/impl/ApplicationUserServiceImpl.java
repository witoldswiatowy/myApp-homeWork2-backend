package com.project.spring.service.impl;

import com.project.spring.exception.ParamsIsEmptyException;
import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.mapper.ApplicationUserMapper;
import com.project.spring.model.ApplicationUser;
import com.project.spring.model.mapper.ApplicationUserMapper2;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.ApplicationUserRoleRepository;
import com.project.spring.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    public ApplicationUserDto addUser(CreateUserRequest request) {
        ApplicationUser newUser = applicationUserMapper.mapCreateUserRequestToApplicationUser(request);

        newUser.setPassword(passwordEncoder.encode(request.getPass()));
        newUser.setRoles(
                new HashSet<>(
                        Collections.singletonList(applicationUserRoleRepository
                                .findByName(defaultNewUserRole)
                                .orElseThrow(EntityNotFoundException::new))));

        return applicationUserMapper.mapApplicationUserToDTO(applicationUserRepository.save(newUser));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationUserDto createUser(CreateUserRequest request) {
        validateCorrectDtoForCrud(request);
        ApplicationUser newUser = ApplicationUserMapper2.createRequestToApplicationUser(request);
        ApplicationUser savedApplicationUser = applicationUserRepository.save(newUser);
        log.info("Create applicationUser {}", savedApplicationUser);
        return ApplicationUserMapper2.toApplicationUserDto(savedApplicationUser);
    }

    @Override
    public List<ApplicationUserDto> listUsers() {
        return applicationUserRepository.findAll()
                .stream()
                .map(applicationUserMapper::mapApplicationUserToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicationUserDto> listUsers2() {
        return ApplicationUserMapper2.toApplicationUserDtos(applicationUserRepository.findAll());
    }

    private void validateCorrectDtoForCrud(CreateUserRequest request) {
        if (request == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        if (request.getLogin() == null || request.getLogin().isBlank()){
            log.error("Can not create applicationUser without login!");
            throw new ParamsIsEmptyException("Can not create applicationUser without login!");
        }
        if (request.getPass() == null || request.getPass().isBlank()){
            log.error("Can not create applicationUser without pass!");
            throw new ParamsIsEmptyException("Can not create applicationUser without pass!");
        }
        if (request.getName() == null || request.getName().isBlank()){
            log.error("Can not create applicationUser without first name!");
            throw new ParamsIsEmptyException("Can not create applicationUser without first name!");
        }
        if (request.getSurname() == null || request.getSurname().isBlank()){
            log.error("Can not create applicationUser without last name!");
            throw new ParamsIsEmptyException("Can not create applicationUser without last name!");
        }
    }
}
