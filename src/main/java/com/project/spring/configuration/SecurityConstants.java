package com.project.spring.configuration;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 02.10.2022
 */
public interface SecurityConstants {
    public static final String TOKEN_SIGNING_KEY = "nEoStRaDa";

    public static final String ROLE_CLAIM = "roles";
    public static final String HEADER_EXPIRATION = "expires_at";
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_AUTH_BEARER_PREFIX = "Bearer:";
    public static final String HEADER_EXPOSED_HEADERS = "Access-Control-Expose-Headers";

    public static final int TOKEN_VALID_DURATION_IN_DAYS = 10;
}
