package com.project.spring.controller;

import com.project.spring.model.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 08.10.2022
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/admin")
    public MessageDTO testAdmin() {
        return new MessageDTO("pong");
    }

    @GetMapping("/moderator")
    public MessageDTO testModerator() {
        return new MessageDTO("pong");
    }

    @GetMapping("/anyone")
    public MessageDTO testAnyone() {
        return new MessageDTO("pong");
    }

    @GetMapping("/public")
    public MessageDTO testPublic() {
        return new MessageDTO("pong");
    }

    @GetMapping("/secured")
    @Secured({ "ROLE_USER" })
    public MessageDTO testSecured() {
        return new MessageDTO("pong");
    }
}
