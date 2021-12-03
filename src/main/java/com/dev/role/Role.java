package com.dev.role;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long roleID;
    @Column(nullable = false)
    String roleName;

    public Role() {
    }
}
