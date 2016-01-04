package com.rmpader.springdrools.fact;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RMPader
 */
public class Suggestions {

    private List<String> suggestedCourseCode = new ArrayList<>();

    public List<String> getSuggestedCourseCodes() {
        return suggestedCourseCode;
    }

    public void addSuggestedCourseCode(String suggestion) {
        suggestedCourseCode.add(suggestion);
    }
}
