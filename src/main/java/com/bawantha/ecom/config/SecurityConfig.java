package com.bawantha.ecom.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            // Lambda can be replaced with method reference - AbstractHttpConfigurer::disable
//            http.csrf(customizer -> customizer.disable()); //cross site request forge
//            http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//            http.formLogin(Customizer.withDefaults()); //Only works for browser forms
//            http.httpBasic(Customizer.withDefaults()); //This for api calls (REST) , Postman
//            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //For every req session changes because it accesses new resource and won't work with form login as expected in browser
//

            //BTS of lambda func
//            Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//                @Override
//                public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//
//                    customizer.disable();
//                }
//            };
//
//            http.csrf(custCsrf);


            //Builder Pattern
            return http
                    .csrf(customizer -> customizer.disable())
                    .authorizeHttpRequests(request -> request
                            .requestMatchers("/api/register", "/api/login")
                            .permitAll()
                            .anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();


            //http.formLogin(Customizer.withDefaults()); //Only works for browser forms

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public UserDetailsService userDetailsService(){

        //example users

//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("bawantha")
//                .password("baw2002")
//                .roles("USER")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("madhushankha")
//                .password("madhu2002")
//                .roles("ADMIN")
//                .build();


        return new InMemoryUserDetailsManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider( ){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
         AuthenticationManager authenticationManager = config.getAuthenticationManager();
         return authenticationManager;

    }



}
