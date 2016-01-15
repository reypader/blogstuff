package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.HelloResponse;
import com.rmpader.basicsecurity.data.model.UserProfile;
import com.rmpader.basicsecurity.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloEndpoint {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/{name}",
                    method = RequestMethod.GET)
    public HelloResponse sayHello(@PathVariable("name") String name) {
        HelloResponse response = new HelloResponse();
        response.setMessage("Hello, " + name);
        UserProfile userProfile = userDetailsService.getCurrentUser().getUserProfile();
        response.setFrom(userProfile);
        return response;
    }
}
