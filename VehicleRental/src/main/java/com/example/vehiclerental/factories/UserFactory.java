package com.example.vehiclerental.factories;

import com.example.vehiclerental.enums.UserEnum;
import com.example.vehiclerental.model.Admin;
import com.example.vehiclerental.model.Buyer;
import com.example.vehiclerental.model.User;
import lombok.NonNull;

public class UserFactory {

    public static User createUser(@NonNull final UserEnum type, @NonNull final String userId,
                                  @NonNull final String name, @NonNull final User.UserProps props) {
        return UserEnum.ADMIN.equals(type) ? new Admin(userId, name, props, type) : new Buyer(userId, name, props, type);
    }
}
