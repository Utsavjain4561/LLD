package com.example.vehiclerental.model;

import com.example.vehiclerental.enums.UserEnum;

public class Admin extends User{
    public Admin(String userId, String name, UserProps props, UserEnum type) {
        super(userId, name, props, type);
    }
}
