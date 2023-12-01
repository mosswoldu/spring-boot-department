package com.mose.springbootProject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllaController {

    @Value("${com.mose}")
    private  String welcomeMessage;

    @GetMapping("hello")

    public String sayHello(){
        return welcomeMessage;
    }
}
