package com.rmpader.basicsecurity.api.resource;

import com.rmpader.basicsecurity.data.model.UserProfile;

/**
 * @author RMPader
 */
public class HelloResponse {

    private String message;
    private UserProfile details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFrom(UserProfile details) {
        this.details = details;
    }

    public UserProfile getFrom() {
        return details;
    }
}
