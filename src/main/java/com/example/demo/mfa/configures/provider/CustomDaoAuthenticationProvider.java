package com.example.demo.mfa.configures.provider;

import com.example.demo.mfa.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";
    private PasswordEncoder passwordEncoder;
    private volatile String userNotFoundEncodedPassword;

    private CustomUserDetailsService customUserDetailsService;
    private UserDetailsPasswordService userDetailsPasswordService;

    public CustomDaoAuthenticationProvider() {
        setPasswordEncoder((BCryptPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    public CustomDaoAuthenticationProvider(CustomUserDetailsService customUserDetailsService) {
        this();
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return null;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
    }
}
