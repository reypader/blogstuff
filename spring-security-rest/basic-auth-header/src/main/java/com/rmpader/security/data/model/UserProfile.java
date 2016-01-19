package com.rmpader.security.data.model;

import com.rmpader.security.util.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author RMPader
 */
@Entity
@Table(name = "users")
public class UserProfile {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.EAGER,
               orphanRemoval = true)
    @JoinColumn(name = "username")
    private List<UserAuthority> authorities = new ArrayList<>();

    private UserProfile() {
    }

    public UserProfile(String username, String password, Authority... authorities) {
        this.username = username;
        this.password = password;
        for (Authority authority : authorities) {
            this.authorities.add(new UserAuthority(this, authority));
        }
        this.enabled = true;
    }

    public List<UserAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
