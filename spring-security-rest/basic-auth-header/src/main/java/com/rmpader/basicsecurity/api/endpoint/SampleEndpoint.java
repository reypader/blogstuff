package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.AddUserRequest;
import com.rmpader.basicsecurity.api.resource.CsrfResponse;
import com.rmpader.basicsecurity.api.resource.HelloResponse;
import com.rmpader.basicsecurity.data.model.UserProfile;
import com.rmpader.basicsecurity.data.repository.UserProfileRepository;
import com.rmpader.basicsecurity.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author RMPader
 */
@RestController
@RequestMapping()
public class SampleEndpoint {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/user/add",
                    method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest request) {
        String password = encoder.encode(request.getPassword());
        List<Authority> authorities = request.getAuthorities();
        UserProfile userProfile = new UserProfile(request.getUsername(), password,
                                                  authorities.toArray(new Authority[authorities.size()]));

        userProfileRepository.save(userProfile);
    }

    @RequestMapping(value = "/hello",
                    method = RequestMethod.GET)
    public HelloResponse sayHello() {
        HelloResponse response = new HelloResponse();
        response.setMessage("Hello, " + getCurrentUser().getUsername());
        return response;
    }


    @RequestMapping(value = "/csrf",
                    method = RequestMethod.GET)
    public CsrfResponse getCsrf(HttpServletRequest request) {
        CsrfResponse response = new CsrfResponse();
        response.setCsrf(((CsrfToken) request.getAttribute("_csrf")).getToken());
        return response;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext()
                                           .getAuthentication()
                                           .getPrincipal();
    }
}
