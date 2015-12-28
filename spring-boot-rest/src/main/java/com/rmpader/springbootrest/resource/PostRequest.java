package com.rmpader.springbootrest.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RMPader
 */

public class PostRequest {

    private String message;
    private String anotherMessage;
    private Map<String, Object> otherProperties = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnotherMessage() {
        return anotherMessage;
    }

    public void setAnotherMessage(String anotherMessage) {
        this.anotherMessage = anotherMessage;
    }

    public Map<String, Object> getOtherProperties() {
        return otherProperties;
    }

    public void setOtherProperties(Map<String, Object> otherProperties) {
        this.otherProperties = otherProperties;
    }
}
