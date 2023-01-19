package org.splitwise.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.splitwise.model.Balance;
import org.splitwise.model.Expense;
import org.splitwise.services.BalanceManagerService;
import org.splitwise.services.ExpenseManagerService;

@Slf4j
@RequiredArgsConstructor
public class BalanceSettlementController {
    private final BalanceManagerService balanceManagerService;
    private final ExpenseManagerService expenseManagerService;

    public Boolean settleBalance(@NonNull final Expense expense, @NonNull final Balance balance,
                                 @NonNull final Double amountToBePaid) {
        val status = balanceManagerService.settleBalance(balance, amountToBePaid);
        if(status && expenseManagerService.settleExpense(expense)){
            log.info("Expense :{}  is settled", expense);
        }
        return status;
    }
}
