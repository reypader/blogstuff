package com.rmpader.security.api.endpoint;

import com.rmpader.security.api.resource.AddUserRequest;
import com.rmpader.security.api.resource.CsrfResponse;
import com.rmpader.security.api.resource.UserDetailsResponse;
import com.rmpader.security.data.model.UserAuthentication;
import com.rmpader.security.data.model.UserAuthority;
import com.rmpader.security.data.model.UserProfile;
import com.rmpader.security.data.repository.UserAuthenticationRepository;
import com.rmpader.security.data.repository.UserAuthorityRepository;
import com.rmpader.security.data.repository.UserProfileRepository;
import com.rmpader.security.util.Admin;
import com.rmpader.security.util.Authority;
import com.rmpader.security.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Admin
    @RequestMapping(value = "/users/add",
                    method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest request) {
        List<Authority> authorities = request.getAuthorities();
        UserProfile userProfile = new UserProfile(request.getUsername(), request.getFullName(), request.getAge(),
                                                  request.getCountry());

        userProfile = userProfileRepository.save(userProfile);

        for (Authority authority : authorities) {
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
        response.setDetails(userProfile);
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
        Object principal = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();

        if (principal instanceof UserDetails) {
            return (UserDetailsImpl) principal;
        } else {
            return UserDetailsImpl.anonymous();
        }
    }
}
