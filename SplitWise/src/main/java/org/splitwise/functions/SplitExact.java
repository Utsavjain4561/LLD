package org.splitwise.functions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.splitwise.model.Balance;
import org.splitwise.model.Expense;
import org.splitwise.model.Split;

import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class SplitExact implements Function<Split, Expense> {
    @SneakyThrows
    @Override
    public Expense apply(Split split) {
        val amount = split.getAmount();
        val userOwedTo  = split.getOwedTo();
        val splits = split.getSplits();
        val totalSum = splits.values().stream().reduce(Double::sum).orElse(0.00);
        if(!totalSum.equals(amount)) {
            throw new Exception("Amount to be split is not equal to individual contributions");
        }
        val balances = splits.entrySet().stream()
                .map(entry -> Balance.builder().owedBy(entry.getKey()).owedTo(userOwedTo)
                        .amount(entry.getValue()).build())
                .collect(Collectors.toList());
        return new Expense(amount, balances);
    }
}
