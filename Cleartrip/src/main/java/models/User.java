package models;

import enums.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class User {
    private final String id;
    private final UserType type;
}
