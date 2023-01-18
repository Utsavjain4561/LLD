package org.splitwise.services;

import org.splitwise.model.Group;
import org.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GroupManagerService {
    private final Map<String, Group> groups = new HashMap<>();

    public Group createGroup(final String groupName) {
        return Optional.ofNullable(groups.get(groupName)).orElseGet(() -> {
            groups.put(groupName, Group.builder().groupName(groupName).build());
            return groups.get(groupName);
        });
    }

    public boolean addUser(final Group group, final User user) {
        return group.getUsers().add(user);
    }
}
