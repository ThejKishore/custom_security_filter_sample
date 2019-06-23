package com.kish.learning.customsecfilter.sec;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class CustAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
       /* if ("user".equals(username) && "password".equals(password)) {*/
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        /*} else {
            throw new BadCredentialsException("Authentication failed");
        }*/
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
