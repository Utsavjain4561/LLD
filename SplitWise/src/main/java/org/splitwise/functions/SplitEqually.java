package org.splitwise.functions;

import lombok.NonNull;
import lombok.val;
import org.splitwise.model.Balance;
import org.splitwise.model.Expense;
import org.splitwise.model.Split;
import org.splitwise.model.User;

import java.util.HashMap;
import java.util.function.Function;


public class SplitEqually implements Function<Split, Expense> {

    @Override
    public Expense apply(@NonNull final Split split) {
        val users = split.getGroup().getUsers();
        val splitAmount = 1.0 * split.getAmount() / users.size();
        val userOwedTo = split.getOwedTo();
        val balances = new HashMap<User, Balance>();
        users.stream().filter(user -> !user.equals(userOwedTo))
                .forEach(user -> balances.put(user,
                        Balance.builder().amount(splitAmount).owedBy(user).owedTo(userOwedTo).build()));
        return new Expense(split.getAmount(), balances);
    }
}
