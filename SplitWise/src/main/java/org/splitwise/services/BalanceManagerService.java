package org.splitwise.services;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.splitwise.model.Balance;
import org.splitwise.model.Expense;

@Slf4j
public class BalanceManagerService {

    public void updateBalance(final Expense expense) {
        expense.getBalances().values().forEach(balance -> {
            val userOwedBy = balance.getOwedBy();
            val userOwedTo = balance.getOwedTo();
            userOwedBy.setTotalOwe(userOwedBy.getTotalOwe() + balance.getAmount());
            userOwedTo.setTotalOwed(userOwedTo.getTotalOwed() + balance.getAmount());
        });
    }

    public Boolean settleBalance(final Balance balance, final Double amountToBePaid) {
        val userOwedBy = balance.getOwedBy();
        val userOwedTo = balance.getOwedTo();
        balance.setAmount(balance.getAmount() - amountToBePaid);
        userOwedTo.setTotalOwed(userOwedTo.getTotalOwed() - amountToBePaid);
        userOwedBy.setTotalOwe(userOwedBy.getTotalOwe() - amountToBePaid);
        if(balance.getAmount()==0.0) {
            balance.setIsSettled(Boolean.TRUE);
        }
        log.info("User : {} paid  Amount: {} to User :{}", userOwedBy.getName(), amountToBePaid, userOwedTo.getName());
        return balance.getIsSettled();
    }
}
