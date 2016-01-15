package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.AddUserRequest;
import com.rmpader.basicsecurity.data.model.UserAuthentication;
import com.rmpader.basicsecurity.data.model.UserAuthority;
import com.rmpader.basicsecurity.data.model.UserProfile;
import com.rmpader.basicsecurity.data.repository.UserAuthenticationRepository;
import com.rmpader.basicsecurity.data.repository.UserAuthorityRepository;
import com.rmpader.basicsecurity.data.repository.UserProfileRepository;
import com.rmpader.basicsecurity.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/user")
public class UserEndpoint {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/add",
                    method = RequestMethod.POST)
    public void sayHello(@RequestBody AddUserRequest request) {
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

}
