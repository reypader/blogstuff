package com.rmpader.springdrools.api.endpoint;

import com.rmpader.springdrools.api.resource.SuggestionResponse;
import com.rmpader.springdrools.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/course")
public class CourseEndpoint {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/suggest",
                    method = RequestMethod.GET)
    public SuggestionResponse suggestCourse(@RequestParam Map<String, String> params) {
        Map<String, Integer> ratings = params.entrySet()
                                             .stream()
                                             .collect(Collectors.toMap(entry -> entry.getKey(),
                                                                       entry -> Integer.valueOf(entry.getValue())));
        return courseService.suggestCourses(ratings);
    }
}
