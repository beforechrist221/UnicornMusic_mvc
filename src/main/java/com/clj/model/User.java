package com.clj.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String email;
    private String username;
    private String password;

    public User(Integer id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public User(Integer id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public User() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
