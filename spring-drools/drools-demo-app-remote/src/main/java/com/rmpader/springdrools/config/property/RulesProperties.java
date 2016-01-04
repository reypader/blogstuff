package com.rmpader.springdrools.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author RMPader
 */
@Component
@ConfigurationProperties(prefix = "admin.rules")
public class RulesProperties {

    @NotNull
    private String workbenchURL;

    @NotNull
    private String repoContextPath;

    @NotNull
    private String username;

    @NotNull
    private String password;    


    public String getRulesRepoPath() {
        return getWorkbenchURL() + getRepoContextPath();
    }

    public String getWorkbenchURL() {
        return workbenchURL;
    }

    public void setWorkbenchURL(String workbenchURL) {
        this.workbenchURL = workbenchURL;
    }

    public String getRepoContextPath() {
        return repoContextPath;
    }

    public void setRepoContextPath(String repoContextPath) {
        this.repoContextPath = repoContextPath;
    }

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
}
