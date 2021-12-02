package com.dev.role;

import com.dev.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long roleID;
    @Column(nullable = false)
    String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }
}
