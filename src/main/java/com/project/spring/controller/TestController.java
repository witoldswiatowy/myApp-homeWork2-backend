package com.project.spring.controller;

import com.project.spring.model.dto.MessageDto;
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
    public MessageDto testAdmin() {
        return new MessageDto("pong");
    }

    @GetMapping("/moderator")
    public MessageDto testModerator() {
        return new MessageDto("pong");
    }

    @GetMapping("/anyone")
    public MessageDto testAnyone() {
        return new MessageDto("pong");
    }

    @GetMapping("/public")
    public MessageDto testPublic() {
        return new MessageDto("pong");
    }

    @GetMapping("/secured")
    @Secured({ "ROLE_USER" })
    public MessageDto testSecured() {
        return new MessageDto("pong");
    }
}
