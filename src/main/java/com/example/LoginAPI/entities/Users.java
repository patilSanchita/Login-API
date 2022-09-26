package com.example.LoginAPI.entities;

public class Users {

    private String username;
    private String password;

    //making constructor
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //adding default constructor with super class
    public Users(){
        super();
    }

    //adding getters and setters.
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

    //overriding toString() method.
    @Override
    public String toString() {
        return "Users{" +
        		"username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
