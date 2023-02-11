package services;

import dao.Storage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.User;

import java.util.List;

@Slf4j
public class RegistrationService {
    private final List<User> leaderboard = Storage.getLeaderboard();
    public User addUser(@NonNull final String name, @NonNull final String department) {
        val user = new User(name, department);
        log.info("New User added : {}", user);
        Storage.addUser(user);
        return user;
    }
}
