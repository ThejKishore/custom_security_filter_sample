package com.kish.learning.customsecfilter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String greetings(){
        return String.format("Hello Logged in user ");
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
