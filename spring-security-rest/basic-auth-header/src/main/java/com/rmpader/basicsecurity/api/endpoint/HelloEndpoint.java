package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.HelloResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public HelloResponse sayHello() {
        HelloResponse response = new HelloResponse();
        response.setMessage("Hello, " + getCurrentUser().getUsername());
        return response;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext()
                                           .getAuthentication()
                                           .getPrincipal();
    }
}
