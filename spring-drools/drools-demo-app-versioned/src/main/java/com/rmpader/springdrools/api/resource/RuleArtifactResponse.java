package com.rmpader.springdrools.api.resource;

/**
 * @author RMPader
 */
public class RuleArtifactResponse {

    private String id;
    private String groupId;
    private String artifactId;
    private String version;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
