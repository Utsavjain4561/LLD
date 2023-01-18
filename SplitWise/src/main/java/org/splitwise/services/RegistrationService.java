package org.splitwise.services;


import org.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RegistrationService {
    private final Map<String, User> userMap = new HashMap<>();
    public User register(final String name, final String email,final Long phoneNo) {
        return Optional.ofNullable(userMap.get(email)).orElseGet(() -> {
            userMap.put(email, new User(name, email, phoneNo));
            return userMap.get(email);
        });
    }
}
