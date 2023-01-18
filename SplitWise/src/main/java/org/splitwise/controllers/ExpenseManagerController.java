package org.splitwise.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.splitwise.model.Expense;
import org.splitwise.model.Split;
import org.splitwise.services.BalanceManagerService;
import org.splitwise.services.ExpenseManagerService;

@RequiredArgsConstructor
public class ExpenseManagerController {
    private final ExpenseManagerService expenseManagerService;
    private final BalanceManagerService balanceManagerService;
    public Expense createExpense(@NonNull final Split split) {
        val expense = expenseManagerService.createExpense(split);
        balanceManagerService.updateBalance(expense);
        return expense;
    }
}
