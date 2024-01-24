package com.example.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;
}
