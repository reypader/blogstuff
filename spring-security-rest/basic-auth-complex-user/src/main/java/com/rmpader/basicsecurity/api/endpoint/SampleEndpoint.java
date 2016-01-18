package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.AddUserRequest;
import com.rmpader.basicsecurity.api.resource.CsrfResponse;
import com.rmpader.basicsecurity.api.resource.UserDetailsResponse;
import com.rmpader.basicsecurity.data.model.UserAuthentication;
import com.rmpader.basicsecurity.data.model.UserAuthority;
import com.rmpader.basicsecurity.data.model.UserProfile;
import com.rmpader.basicsecurity.data.repository.UserAuthenticationRepository;
import com.rmpader.basicsecurity.data.repository.UserAuthorityRepository;
import com.rmpader.basicsecurity.data.repository.UserProfileRepository;
import com.rmpader.basicsecurity.security.Authority;
import com.rmpader.basicsecurity.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SampleEndpoint {

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/users/add",
                    method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest request) {
        List<Authority> authorities = request.getAuthorities();
        UserProfile userProfile = new UserProfile(request.getUsername(), request.getFullName(), request.getAge(),
                                                  request.getCountry());

        userProfile = userProfileRepository.save(userProfile);

        for (Authority authority : authorities) {
            System.out.println(authorities);
            userAuthorityRepository.save(new UserAuthority(userProfile, authority));
        }

        String password = encoder.encode(request.getPassword());
        UserAuthentication userAuthentication = new UserAuthentication(userProfile, password);
        userAuthenticationRepository.save(userAuthentication);
    }

    @RequestMapping(value = "/me",
                    method = RequestMethod.GET)
    public UserDetailsResponse me() {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setUsername(getCurrentUser().getUserProfile()
                                             .getUsername());
        UserProfile userProfile = getCurrentUser().getUserProfile();
        response.setFrom(userProfile);
        return response;
    }

    @RequestMapping(value = "/csrf",
                    method = RequestMethod.GET)
    public CsrfResponse getCsrf(HttpServletRequest request) {
        CsrfResponse response = new CsrfResponse();
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        response.setCsrf(csrfToken.getToken());
        return response;
    }

    public UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext()
                                                      .getAuthentication()
                                                      .getPrincipal();
    }
}
