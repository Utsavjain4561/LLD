package org.splitwise.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Expense {
    private final Double amount;
    private final List<Balance> balances;
    private final Boolean isSettled = Boolean.FALSE;
}
