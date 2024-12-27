package com.bawantha.ecom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            // Lambda can be replaced with method reference - AbstractHttpConfigurer::disable
            http.csrf(customizer -> customizer.disable());
            http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
            http.formLogin(Customizer.withDefaults()); //Only works for browser forms
            http.httpBasic(Customizer.withDefaults()); //This for api calls (REST) , Postman




            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
