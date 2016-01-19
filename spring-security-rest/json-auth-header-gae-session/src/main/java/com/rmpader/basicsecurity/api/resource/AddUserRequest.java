package com.rmpader.basicsecurity.api.resource;

import com.rmpader.basicsecurity.security.Authority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RMPader
 */
public class AddUserRequest {

    private String username;
    private String password;
    private String fullName;
    private int age;
    private String country;
    private List<Authority> authorities = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
