package com.bawantha.ecom.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
