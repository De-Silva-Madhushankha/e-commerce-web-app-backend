package com.bawantha.ecom.controller;


import com.bawantha.ecom.model.User;
import com.bawantha.ecom.service.EcomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class userController {

    @Autowired
    EcomUserDetailsService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){

        List<User> users = service.getUsers();

        if (users.size() == 0)
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.FOUND);

    }





}
