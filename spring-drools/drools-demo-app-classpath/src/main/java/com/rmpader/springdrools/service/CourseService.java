package com.rmpader.springdrools.service;

import com.rmpader.springdrools.api.resource.SuggestionResponse;
import com.rmpader.springdrools.fact.SubjectRating;
import com.rmpader.springdrools.fact.Suggestions;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author RMPader
 */
@Service
public class CourseService {

    @Autowired
    private KieContainer kieContainer;

    public SuggestionResponse suggestCourses(Map<String, Integer> subjectRating) {
        StatelessKieSession courseMatchSession = kieContainer.newStatelessKieSession();
        SuggestionResponse response = new SuggestionResponse();
        List<Object> facts = subjectRating.entrySet()
                                          .stream()
                                          .map(stringStringEntry -> new SubjectRating(stringStringEntry.getKey(),
                                                                                      stringStringEntry.getValue()))
                                          .collect(toList());

        Suggestions suggestions = new Suggestions();
        courseMatchSession.setGlobal("suggestions", suggestions);
        courseMatchSession.execute(facts);

        suggestions.getSuggestedCourseCodes()
                   .forEach(response::addSuggestion);

        return response;
    }

}
