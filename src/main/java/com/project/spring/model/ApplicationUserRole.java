package com.project.spring.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 02.10.2022
 */
@Data // getter // setter // toString // equalsAndHashCode // requiredArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    // Linked security entities
    //
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ApplicationUser> users;
}
