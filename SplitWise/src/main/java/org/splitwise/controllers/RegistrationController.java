package org.splitwise.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.splitwise.model.User;
import org.splitwise.services.RegistrationService;

@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    public User register(@NonNull final String name, @NonNull final String email,
                         @NonNull final Long phoneNo) {
        return registrationService.register(name, email, phoneNo);
    }
}
