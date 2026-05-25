package com.yango.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PublicController {

    @GetMapping("/public")
    public String publicAccess(){
        return UUID.randomUUID().toString();
    }
}
