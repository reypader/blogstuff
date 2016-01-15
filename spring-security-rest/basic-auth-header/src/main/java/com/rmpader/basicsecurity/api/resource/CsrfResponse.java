package com.rmpader.basicsecurity.api.resource;

/**
 * @author RMPader
 */
public class CsrfResponse {

    private String csrf;

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }
}
