package org.splitwise.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.splitwise.model.Group;
import org.splitwise.model.User;
import org.splitwise.services.GroupManagerService;

@RequiredArgsConstructor
public class GroupManagerController {
    private final GroupManagerService groupManagerService;
    public Group createGroup(@NonNull  final String groupName) {
        return groupManagerService.createGroup(groupName);
    }

    public boolean addUser(final Group group, final User user) {
        return groupManagerService.addUser(group, user);
    }
}
