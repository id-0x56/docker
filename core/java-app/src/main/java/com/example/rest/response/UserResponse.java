package com.example.rest.response;

import java.util.List;

import com.example.rest.entity.Activity;
import com.example.rest.entity.Profile;
import com.example.rest.entity.Role;

public class UserResponse {
    private Long id;

    private String email;

    private Profile profile;

    private List<Role> roles;

    private Activity activity;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
