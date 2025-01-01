package com.bawantha.ecom.controller;


import com.bawantha.ecom.model.User;
import com.bawantha.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){

        List<User> users = service.getUsers();

        if (users.size() == 0)
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.FOUND);

    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User newUser =  service.register(user);
        System.out.println(newUser);

        if( newUser != null)
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        return new ResponseEntity<>(newUser, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        //System.out.println(user);
        return service.verify(user);

    }



}
