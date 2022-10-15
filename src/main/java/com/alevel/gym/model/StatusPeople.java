package com.alevel.gym.model;

import org.springframework.security.core.GrantedAuthority;

public enum StatusPeople implements GrantedAuthority {
    COACH, ADMIN, VISITOR, OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}
