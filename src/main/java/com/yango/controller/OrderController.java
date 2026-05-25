package com.yango.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class OrderController {

    @GetMapping("/")
    public String greetings(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String token = jwt.getTokenValue();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .GET()
                                             .uri(URI.create("http://localhost:8082/"))
                                             .header("Authorization", "Bearer " + token)
                                             .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return "Available inventory " + response.body();
    }

}
