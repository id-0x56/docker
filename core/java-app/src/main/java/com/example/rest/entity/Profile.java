package com.example.rest.entity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Invalid first_name: First name is NULL")
    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @NotNull(message = "Invalid last_name: Last name is NULL")
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @Column(name = "verify", nullable = false, columnDefinition = "boolean default false")
    private boolean verify;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    protected Profile() {}

    public Profile(String firstName, String lastName, boolean verify, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.verify = verify;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVerify() {
        return this.verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
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

        // Profile profile = (Profile) object;

        if (!(object instanceof Profile profile)) {
            return false;
        }

        return this.isVerify() == profile.isVerify()
            && Objects.equals(this.getId(), profile.getId())
            && Objects.equals(this.getFirstName(), profile.getFirstName())
            && Objects.equals(this.getLastName(), profile.getLastName())
            && Objects.equals(this.getUser(), profile.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getFirstName(),
            getLastName(),
            isVerify(),
            getUser()
        );
    }
}
