package com.example.diploma.Entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ROLE_ADMIN, ROLE_HEAD, ROLE_CONTROLLER, ROLE_PROJECT_HEAD;

    @Override
    public String getAuthority() {
        return name();
    }
}
