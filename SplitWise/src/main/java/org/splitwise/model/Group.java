package org.splitwise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class Group {
    private final String groupName;
    @Builder.Default
    private final List<User> users = new ArrayList<>();
    @Setter
    private Expense expense;
}
