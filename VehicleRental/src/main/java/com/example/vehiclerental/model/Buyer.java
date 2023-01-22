package com.example.vehiclerental.model;


import com.example.vehiclerental.enums.UserEnum;

public class Buyer extends User {
    public Buyer(String userId, String name, UserProps props, UserEnum type) {
        super(userId, name, props, type);
    }
}
