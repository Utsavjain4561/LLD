package org.splitwise.functions;

import lombok.NonNull;
import lombok.val;
import org.splitwise.model.Balance;
import org.splitwise.model.Expense;
import org.splitwise.model.Split;

import java.util.function.Function;
import java.util.stream.Collectors;


public class SplitEqually implements Function<Split, Expense> {

    @Override
    public Expense apply(@NonNull final Split split) {
        val users = split.getGroup().getUsers();
        val splitAmount = 1.0 * split.getAmount() / users.size();
        val userOwedTo = split.getOwedTo();
        val balances = users.stream().map(user -> Balance.builder().amount(splitAmount)
                .owedBy(user).owedTo(userOwedTo).build())
                .collect(Collectors.toList());
        return new Expense(split.getAmount(), balances);
    }
}
