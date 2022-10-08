package com.project.spring.configuration;

import com.project.spring.model.config.DefaultUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 02.10.2022
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "application.default")
public class InitialUsersConfiguration {
    private List<String> roles;
    private List<DefaultUser> users;
}
