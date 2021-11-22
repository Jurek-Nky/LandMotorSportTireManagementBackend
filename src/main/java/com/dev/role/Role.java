package com.dev.role;

import com.dev.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long roleID;
    @Column(nullable = false)
    String roleName;
    @OneToMany(mappedBy = "role")
    List<User> users;

    public Role(Long roleID, String rollenName, List<User> users) {
        this.roleID = roleID;
        this.roleName = rollenName;
        this.users = users;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }
}
