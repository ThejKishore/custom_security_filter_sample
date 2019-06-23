package com.kish.learning.customsecfilter.sec.filter;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;


public class CustomSecFilter extends GenericFilterBean {

    private static final Logger log = LoggerFactory.getLogger(CustomSecFilter.class);



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String oauthHeader = httpRequest.getHeader("OAuth");
        if(StringUtils.isNotEmpty(oauthHeader)){
            log.info("authorized access");

            //TODO create Authentication Object from the custom token header and
             SecurityContextHolder.getContext().setAuthentication(getAuthentication(oauthHeader));
            // set it to the SecurityContextHolder.getContext().setAuthentication();
            chain.doFilter(request,response);

        }else {
            log.info("unauthorized access");
            ((HttpServletResponse) response).sendError(401, "Unauthorized access");
        }

    }


    public Authentication getAuthentication(String token) {
        /* Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
         */

        Collection<? extends GrantedAuthority> authorities =
                Arrays.asList("user".split(",")).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User("Guest_"+new Random().nextInt(100)+"_"+token, "",
                authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

}
