package com.rmpader.security.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author RMPader
 */
@Entity
@Table(name = "user_authentications")
public class UserAuthentication implements Serializable{

    @Id
    private String username;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "username")
    private UserProfile userProfile;

    @Column(name = "password",
            nullable = false)
    private String password;

    private UserAuthentication(){}

    public UserAuthentication(UserProfile userProfile, String password) {
        this.username = userProfile.getUsername();
        this.userProfile = userProfile;
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAuthentication that = (UserAuthentication) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
