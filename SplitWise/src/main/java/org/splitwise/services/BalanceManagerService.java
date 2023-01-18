package org.splitwise.services;

import lombok.val;
import org.splitwise.model.Expense;

public class BalanceManagerService {

    public void updateBalance(final Expense expense) {
        expense.getBalances().forEach(balance -> {
            val userOwedBy = balance.getOwedBy();
            val userOwedTo = balance.getOwedTo();
            userOwedBy.setTotalOwe(userOwedBy.getTotalOwe() + balance.getAmount());
            userOwedTo.setTotalOwed(userOwedTo.getTotalOwed() + balance.getAmount());
        });
    }
}
