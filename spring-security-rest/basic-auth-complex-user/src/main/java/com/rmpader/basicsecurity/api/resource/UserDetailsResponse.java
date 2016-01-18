package com.rmpader.basicsecurity.api.resource;

import com.rmpader.basicsecurity.data.model.UserProfile;

/**
 * @author RMPader
 */
public class UserDetailsResponse {

    private String username;
    private UserProfile details;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFrom(UserProfile details) {
        this.details = details;
    }

    public UserProfile getFrom() {
        return details;
    }
}
