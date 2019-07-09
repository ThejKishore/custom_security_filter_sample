package com.kish.learning.customsecfilter.sec.config;

import com.kish.learning.customsecfilter.sec.filter.CustomSecFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class OverrideSecConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/api").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api2").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterAfter(new CustomSecFilter(), BasicAuthenticationFilter.class);



//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        //
//        http.antMatcher("/hello**")
//                .authorizeRequests()
//                .anyRequest().authenticated();
//
//
//        http.anonymous()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api").permitAll()
//                .antMatchers("/api2").permitAll();
//
//        http.addFilterBefore(new CustomSecFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
