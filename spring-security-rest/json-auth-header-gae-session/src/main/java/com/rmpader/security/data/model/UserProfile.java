package com.rmpader.security.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author RMPader
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile implements Serializable {

    @Id
    private String username;

    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.EAGER,
               orphanRemoval = true)
    @JoinColumn(name = "username")
    private List<UserAuthority> authorities = new ArrayList<>();

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;

    private UserProfile() {
    }

    public UserProfile(String username, String fullName, int age, String country) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.country = country;
    }

    public List<UserAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserProfile that = (UserProfile) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
