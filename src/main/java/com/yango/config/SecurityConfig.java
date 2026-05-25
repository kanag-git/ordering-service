package com.yango.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                           .authorizeHttpRequests(auth ->
                                           auth.requestMatchers("/public").permitAll()
                                                              .anyRequest().authenticated())
                .oauth2ResourceServer(oAuth -> oAuth.jwt(withDefaults()))
                           .build();

    }
}
