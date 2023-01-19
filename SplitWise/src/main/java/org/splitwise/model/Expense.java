package org.splitwise.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class Expense {
    private final Double amount;
    private final Map<User, Balance> balances;

    @Setter
    private Integer settledBills = 0;

    @Setter
    private Boolean isSettled = Boolean.FALSE;
}
