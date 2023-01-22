package com.example.vehiclerental.model;

import com.example.vehiclerental.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class User {
    private String userId;
    private String name;
    private UserProps props;
    private UserEnum type;

    @RequiredArgsConstructor
    @Builder
    @Getter
    public static class UserProps {
        private final String email;
        private final String address;
        private final Long phoneNo;
    }
}
