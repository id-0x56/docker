package com.example.rest.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "last_login_ip", length = 64)
    private String lastLoginIP;

    @Column(name = "last_login_at", nullable = false, columnDefinition= "timestamp with time zone")
    private LocalDateTime lastLoginAt;

    @Column(name = "created_at", nullable = false, columnDefinition= "timestamp with time zone")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition= "timestamp with time zone")
    private LocalDateTime updatedAt;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    protected Activity() {}

    public Activity(String lastLoginIP, LocalDateTime lastLoginAt, LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.lastLoginIP = lastLoginIP;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastLoginIP() {
        return this.lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public LocalDateTime getLastLoginAt() {
        return this.lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        // if (object == null || this.getClass() != object.getClass()) {
        //     return false;
        // }

        // Activity activity = (Activity) object;

        if (!(object instanceof Activity activity)) {
            return false;
        }

        return Objects.equals(this.getId(), activity.getId())
            && Objects.equals(this.getLastLoginIP(), activity.getLastLoginIP())
            && Objects.equals(this.getLastLoginAt(), activity.getLastLoginAt())
            && Objects.equals(this.getCreatedAt(), activity.getCreatedAt())
            && Objects.equals(this.getUpdatedAt(), activity.getUpdatedAt())
            && Objects.equals(this.getUser(), activity.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getId(),
            this.getLastLoginIP(),
            this.getLastLoginAt(),
            this.getCreatedAt(),
            this.getUpdatedAt(),
            this.getUser()
        );
    }
}
