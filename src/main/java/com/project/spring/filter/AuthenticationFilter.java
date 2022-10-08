package com.project.spring.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.project.spring.configuration.SecurityConstants.*;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 02.10.2022
 */
@Slf4j
public class AuthenticationFilter extends BasicAuthenticationFilter {
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTH);

        if (header != null && header.startsWith(HEADER_AUTH_BEARER_PREFIX)) {
            String token = header.replace(HEADER_AUTH_BEARER_PREFIX, "");

            UsernamePasswordAuthenticationToken springToken = verifyToken(token);
            SecurityContextHolder.getContext().setAuthentication(springToken);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken verifyToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(TOKEN_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        String roles = claims.get(ROLE_CLAIM, String.class);

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                Arrays.stream(roles.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}
