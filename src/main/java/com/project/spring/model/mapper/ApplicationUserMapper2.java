package com.project.spring.model.mapper;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUserMapper2 {

    private ApplicationUserMapper2() {
    }

    public static ApplicationUserDto toApplicationUserDto(ApplicationUser applicationUser) {
        if (applicationUser == null) {
            return null;
        }

        return ApplicationUserDto.builder()
                .id(applicationUser.getId())
                .createDate(applicationUser.getCreateDate())
                .updateDate(applicationUser.getUpdateDate())
                .version(applicationUser.getVersion())
                .name(applicationUser.getFirstName())
                .surname(applicationUser.getLastName())
                .email(applicationUser.getEmail())
                .phoneNumber(applicationUser.getPhoneNumber())
                .build();
    }

    public static ApplicationUser toApplicationUser(ApplicationUserDto applicationUserDto) {
        if (applicationUserDto == null) {
            return null;
        }

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(applicationUserDto.getId());
        applicationUser.setCreateDate(applicationUserDto.getCreateDate());
        applicationUser.setUpdateDate(applicationUserDto.getUpdateDate());
        applicationUser.setVersion(applicationUserDto.getVersion());
        applicationUser.setFirstName(applicationUserDto.getName());
        applicationUser.setLastName(applicationUserDto.getSurname());
        applicationUser.setEmail(applicationUserDto.getEmail());
        applicationUser.setPhoneNumber(applicationUserDto.getPhoneNumber());
        return applicationUser;
    }

    public static List<ApplicationUserDto> toApplicationUserDtos(List<ApplicationUser> applicationUsers) {
        return applicationUsers.stream()
                .map(ApplicationUserMapper2::toApplicationUserDto)
                .collect(Collectors.toList());
    }

    public static ApplicationUser createRequestToApplicationUser(CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstName(request.getName());
        applicationUser.setLastName(request.getSurname());
        applicationUser.setEmail(request.getEmail());
        applicationUser.setPhoneNumber(request.getPhoneNumber());
        return applicationUser;
    }
}
