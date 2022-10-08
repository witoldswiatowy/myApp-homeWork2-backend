package com.project.spring.configuration;

import com.project.spring.model.config.DefaultUser;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.ApplicationUserRoleRepository;
import com.project.spring.model.ApplicationUser;
import com.project.spring.model.ApplicationUserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 02.10.2022
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final InitialUsersConfiguration initialUsersConfiguration;

    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository applicationUserRepository;

    private final PasswordEncoder passwordEncoder;

    // Uruchomi się gdy wszystkie beany zostaną załadowane
    @EventListener(ContextRefreshedEvent.class)
    public void initializeDatabase() {
        log.info(String.format("Initial users: %s", initialUsersConfiguration));

        initializeRoles(initialUsersConfiguration.getRoles());
        initializeUsers(initialUsersConfiguration.getUsers());
    }

    private void initializeUsers(List<DefaultUser> users) {
        users.stream()
                .filter(defaultUser -> !applicationUserRepository.existsByUsername(defaultUser.getUsername()))
                .map(this::mapDefaultUserToApplicationUser)
                .forEach(applicationUserRepository::save);
    }

    private ApplicationUser mapDefaultUserToApplicationUser(DefaultUser defaultUser) {
        Set<ApplicationUserRole> userRoles = defaultUser.getRoles()
                .stream()
                .map(applicationUserRoleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        return ApplicationUser.builder()
                .password(passwordEncoder.encode(defaultUser.getPassword()))
                .username(defaultUser.getUsername())
                .roles(userRoles)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
    }

    private void initializeRoles(List<String> roleNames) {
        for (String roleName : roleNames) {
            if (!applicationUserRoleRepository.existsByName(roleName)) {
                createRoleWithName(roleName);
            }
        }
    }

    private void createRoleWithName(String roleName) {
        log.info(String.format("Creating role: %s", roleName));
        ApplicationUserRole role = ApplicationUserRole.builder()
                .name(roleName)
                .build();

        applicationUserRoleRepository.save(role);
    }
}
