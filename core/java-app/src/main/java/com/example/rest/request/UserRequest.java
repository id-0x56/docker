package com.example.rest.request;

import java.util.List;

import com.example.rest.entity.Profile;
import com.example.rest.entity.Role;

public class UserRequest {
    private String email;

    private String password;

    private Profile profile;

    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
