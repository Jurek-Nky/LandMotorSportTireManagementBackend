package com.dev.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "userSettings")
public class UserSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Id;
    @Column()
    boolean getWeatherNotifications;
    @Column()
    boolean getOrderNotifications;
    @Column()
    boolean darkMode;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_userid", nullable = false)
    @JsonIgnore
    private User user;

    public UserSettings() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isGetWeatherNotifications() {
        return getWeatherNotifications;
    }

    public void setGetWeatherNotifications(boolean getWeatherNotifications) {
        this.getWeatherNotifications = getWeatherNotifications;
    }

    public boolean isGetOrderNotifications() {
        return getOrderNotifications;
    }

    public void setGetOrderNotifications(boolean getOrderNotifications) {
        this.getOrderNotifications = getOrderNotifications;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
