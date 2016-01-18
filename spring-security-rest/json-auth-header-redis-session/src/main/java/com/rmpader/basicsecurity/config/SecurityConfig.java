package com.rmpader.basicsecurity.config;

import com.rmpader.basicsecurity.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * @author RMPader
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/me", "/login", "/csrf").permitAll()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
            .and()
                .formLogin()
                    .defaultSuccessUrl("/me", true)
            .and()
                .logout()
                    .logoutSuccessUrl("/me");
        // @formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
                                UserDetailsServiceImpl userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
