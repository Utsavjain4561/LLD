package org.splitwise.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.splitwise.enums.SplitModeEnum;
import org.splitwise.functions.SplitEqually;
import org.splitwise.functions.SplitExact;
import org.splitwise.model.Expense;
import org.splitwise.model.Split;

import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class ExpenseManagerService {
    private final Map<SplitModeEnum, Function<Split, Expense>> splitFunctionMap = Map.of(
            SplitModeEnum.EQUAL, new SplitEqually(),
            SplitModeEnum.EXACT, new SplitExact()
    );

    public Expense createExpense(final Split split) {
        val expense = splitFunctionMap.get(split.getSplitMode()).apply(split);
        split.getGroup().setExpense(expense);
        return expense;
    }

    public Boolean settleExpense(final Expense expense) {
        expense.setSettledBills(expense.getSettledBills() + 1);
        if (expense.getSettledBills() == expense.getBalances().size()) {
            expense.setIsSettled(Boolean.TRUE);
        }
        return expense.getIsSettled();
    }
}

