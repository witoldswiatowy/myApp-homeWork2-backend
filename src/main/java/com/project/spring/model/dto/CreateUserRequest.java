package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String login;
    private String pass;

    private String name;
    private String surname;
}
