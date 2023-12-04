package com.example.rest.request;

import java.util.Set;

import com.example.rest.entity.Profile;
import com.example.rest.entity.Role;

import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotNull(message = "Invalid email: Email is NULL")
    private String email;

    @NotNull(message = "Invalid password: Password is NULL")
    private String password;

    @NotNull(message = "Invalid profile: Profile is NULL")
    private Profile profile;

    private Set<Role> roles;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
