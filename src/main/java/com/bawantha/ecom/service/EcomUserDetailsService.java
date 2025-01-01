package com.bawantha.ecom.service;

import com.bawantha.ecom.model.User;
import com.bawantha.ecom.model.UserPrincipal;
import com.bawantha.ecom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);

        if(user == null){
            System.out.println("User Not Found!!");
            throw new UsernameNotFoundException("User Not Found!");
        }

        return new UserPrincipal(user);
    }

    public List<User> getUsers(){

        return repo.findAll();
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
