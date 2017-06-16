package ru.isakovalexey.lunch.model;

import org.springframework.security.core.GrantedAuthority;
/**
 * Created by user on 29.05.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
