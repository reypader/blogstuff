package com.rmpader.basicsecurity.security;

import com.rmpader.basicsecurity.data.model.UserAuthentication;
import com.rmpader.basicsecurity.data.model.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RMPader
 */
public class UserDetailsImpl implements UserDetails {

    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private UserAuthentication userAuthentication;

    public UserDetailsImpl(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
        this.authorities = userAuthentication.getUserProfile()
                                             .getAuthorities()
                                             .stream()
                                             .map(userAuthority ->
                                                          new SimpleGrantedAuthority(userAuthority.getId()
                                                                                                  .getAuthority()))
                                             .collect(Collectors.toList());
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public UserProfile getUserProfile() {
        return userAuthentication.getUserProfile();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    @Override
    public String getPassword() {
        return userAuthentication.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuthentication.getUserProfile()
                                 .getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
