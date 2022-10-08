package com.project.spring.model.mapper;

import com.project.spring.model.dto.ApplicationUserDTO;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Mapper(componentModel = "spring", imports = {java.util.stream.Collectors.class})
public interface ApplicationUserMapper {

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "username", source = "login"),
            @Mapping(target = "password", source = "pass"),
            @Mapping(target = "firstName", source = "name"),
            @Mapping(target = "lastName", source = "surname"),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "accountNonExpired", constant = "true"),
            @Mapping(target = "accountNonLocked", constant = "true"),
            @Mapping(target = "credentialsNonExpired", constant = "true"),
    })
    ApplicationUser mapCreateUserRequestToApplicationUser(CreateUserRequest request);

    @Mappings(value = {
            @Mapping(source = "id", target="id"),
            @Mapping(source = "username", target = "login"),
            @Mapping(source = "firstName", target = "name"),
            @Mapping(source = "lastName", target = "surname"),
            @Mapping(expression = "java(applicationUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))", target = "roles")
    })
    ApplicationUserDTO mapApplicationUserToDTO(ApplicationUser applicationUser);

}