package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    private String login;
    private List<String> roles;
}
