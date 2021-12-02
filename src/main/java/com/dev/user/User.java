package com.dev.user;

import com.dev.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Userid;

    @Column(nullable = false)
    String vorName;

    @Column(nullable = false)
    String nachName;

    @Transient
    String roleName;

    @Column(nullable = false)
    String password;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "roleID")
    @JsonIgnore
    Role role;

    public User(Long userid, String vorName, String nachName) {
        Userid = userid;
        this.vorName = vorName;
        this.nachName = nachName;
    }

    public User(String vorName, String nachName) {
        this.vorName = vorName;
        this.nachName = nachName;
    }

    public User(String vorName, String nachName, Role role) {
        this.vorName = vorName;
        this.nachName = nachName;
        this.role = role;
    }

    public User() {
    }

    public Long getUserid() {
        return Userid;
    }

    public void setUserid(Long userid) {
        Userid = userid;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public Role getRolle() {
        return role;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRolle(Role role) {
        this.role = role;
    }
}
