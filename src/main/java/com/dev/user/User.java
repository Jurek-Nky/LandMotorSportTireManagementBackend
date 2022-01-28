package com.dev.user;

import com.dev.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Userid;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "roleID")
    @JsonIgnore
    Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettings userSettings;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public Long getUserid() {
        return Userid;
    }

    public void setUserid(Long userid) {
        Userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRolle() {
        return role;
    }

    public void setRolle(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
