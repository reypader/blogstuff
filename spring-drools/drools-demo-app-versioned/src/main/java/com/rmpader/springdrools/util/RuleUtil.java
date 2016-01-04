package com.rmpader.springdrools.util;

/**
 * @author RMPader
 */
public class RuleUtil {

    public static String createPath(String groupId, String artifactId, String version) {
        String groupPath = groupId.replace(".", "/");
        return groupPath
                + "/"
                + artifactId
                + "/"
                + version
                + "/"
                + artifactId + "-" + version + ".jar";
    }
}
