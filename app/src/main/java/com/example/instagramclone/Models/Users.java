package com.example.instagramclone.Models;

public class Users {
    private String phone ;
    private String Email;
    private String id;
    private String username;

    public Users(String email, String id, String username) {
        this.phone = "";
        Email = email;
        this.id = id;
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
