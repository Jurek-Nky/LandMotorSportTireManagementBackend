package com.dev.security;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public enum ApplicationUserPermission {
    TIRE_READ("tire:read"),
    TIRE_WRITE("tire:write"),
    TIRE_DELETE("tire:delete"),
    RACE_READ("race:read"),
    RACE_WRITE("race:write"),
    WEATHER_READ("weather:read"),
    WEATHER_WRITE("weather:write"),
    WEATHER_DELETE("weather:delete"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    INGENIEUR_VARIABLES("ingenieur");

    private final String permisson;

    ApplicationUserPermission(String permission) {
        this.permisson = permission;
    }

    public String getPermisson() {
        return permisson;
    }
}
