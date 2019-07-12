package com.kish.learning.customsecfilter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String greetings(Principal principal){
        return String.format("Hello Logged in user %s",principal.getName());
    }

    @GetMapping("/api")
    public String greetingsApi(){
        return String.format("Hello Logged in user ");
    }

    @GetMapping("/api2")
    public String greetingsApi2(){
        return String.format("Hello Logged in user ");
    }

}
