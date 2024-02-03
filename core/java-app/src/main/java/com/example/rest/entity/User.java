package com.example.rest.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Invalid email: Email is NULL")
    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @NotNull(message = "Invalid password: Password is NULL")
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @NotNull(message = "Invalid profile: Profile is NULL")
    @OneToOne(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    @PrimaryKeyJoinColumn
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    @PrimaryKeyJoinColumn
    private Activity activity;

    protected User() {}

    public User(String email, String password, Profile profile, Set<Role> roles, Activity activity) {
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.roles = roles;
        this.activity = activity;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        // if (object == null || this.getClass() != object.getClass()) {
        //     return false;
        // }

        // User user = (User) object;

        if (!(object instanceof User user)) {
            return false;
        }

        return Objects.equals(this.getId(), user.getId())
            && Objects.equals(this.getEmail(), user.getEmail())
            && Objects.equals(this.getPassword(), user.getPassword())
            && Objects.equals(this.getProfile(), user.getProfile())
            && Objects.equals(this.getRoles(), user.getRoles())
            && Objects.equals(this.getActivity(), user.getActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getId(),
            this.getEmail(),
            this.getPassword(),
            this.getProfile(),
            this.getRoles(),
            this.getActivity()
        );
    }
}
