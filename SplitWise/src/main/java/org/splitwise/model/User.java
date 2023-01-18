package org.splitwise.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class User {
    private final String name;
    private final String email;
    private final Long phoneNo;
    @Setter
    private Double totalOwed = 0.00;
    @Setter
    private Double totalOwe = 0.00;
}
