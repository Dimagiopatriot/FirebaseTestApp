package com.sdmitriy.firebasetestapp.model.entity;

public class UserData {

    private String email;
    private String userId;

    public UserData(String email, String userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }
}
