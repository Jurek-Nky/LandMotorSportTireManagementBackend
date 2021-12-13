package com.dev.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.dev.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(
            TIRE_READ, TIRE_WRITE, TIRE_DELETE,
            RACE_READ, RACE_WRITE,
            WEATHER_READ, WEATHER_WRITE, WEATHER_DELETE,
            USER_READ, USER_WRITE)),
    MANAGER(Sets.newHashSet(
            TIRE_READ, TIRE_WRITE, TIRE_DELETE,
            RACE_READ,
            WEATHER_READ, WEATHER_WRITE, WEATHER_DELETE)),
    INGENIEUR(Sets.newHashSet(
            TIRE_READ, TIRE_WRITE,
            RACE_READ,
            WEATHER_READ,
            INGENIEUR_VARIABLES)),
    EMPLOYEE(Sets.newHashSet(
            WEATHER_WRITE, WEATHER_READ,
            TIRE_READ,
            RACE_READ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermisson()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
