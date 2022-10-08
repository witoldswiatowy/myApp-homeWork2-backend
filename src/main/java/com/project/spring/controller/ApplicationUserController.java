package com.project.spring.controller;

import com.project.spring.model.dto.ApplicationUserDTO;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') && hasRole('MODERATOR')")
    public List<ApplicationUserDTO> getListOfUsers(){
        return applicationUserService.listUsers();
    }

    @PostMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUserDTO postNewUser(@RequestBody CreateUserRequest request){
        return applicationUserService.addUser(request);
    }
}







