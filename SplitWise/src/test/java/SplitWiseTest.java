import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.splitwise.controllers.BalanceSettlementController;
import org.splitwise.controllers.ExpenseManagerController;
import org.splitwise.controllers.GroupManagerController;
import org.splitwise.controllers.RegistrationController;
import org.splitwise.enums.SplitModeEnum;
import org.splitwise.model.Split;
import org.splitwise.services.BalanceManagerService;
import org.splitwise.services.ExpenseManagerService;
import org.splitwise.services.GroupManagerService;
import org.splitwise.services.RegistrationService;

import java.util.Map;

public class SplitWiseTest {

    private RegistrationController registrationController;
    private GroupManagerController groupManagerController;
    private ExpenseManagerController expenseManagerController;
    private BalanceSettlementController balanceSettlementController;

    @BeforeEach
    public void setup() {
        val registrationService = new RegistrationService();
        val groupManagerService = new GroupManagerService();
        val expenseManagerService = new ExpenseManagerService();
        val balanceManagerService = new BalanceManagerService();
        this.registrationController = new RegistrationController(registrationService);
        this.groupManagerController = new GroupManagerController(groupManagerService);
        this.expenseManagerController = new ExpenseManagerController(expenseManagerService, balanceManagerService);
        this.balanceSettlementController = new BalanceSettlementController(balanceManagerService, expenseManagerService);

    }

    @Test
    public void register() {
        val user1 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        val user2 = registrationController.register("user2", "user2@gmail.com", 3743847880L);
        val user3 = registrationController.register("user3", "user3@gmail.com", 3743847880L);
        val user4 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        Assertions.assertEquals(user1.getEmail(), "user1@gmail.com");
        Assertions.assertEquals(user1, user4);
    }

    @Test
    public void createGroup() {
        val user1 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        val user2 = registrationController.register("user2", "user2@gmail.com", 3743847880L);
        val user3 = registrationController.register("user3", "user3@gmail.com", 3743847880L);
        val group = groupManagerController.createGroup("group1");
        Assertions.assertEquals(group.getUsers().size(), 0);
        Assertions.assertEquals(groupManagerController.addUser(group, user1), Boolean.TRUE);
        groupManagerController.addUser(group, user2);
        groupManagerController.addUser(group, user3);
        Assertions.assertEquals(group.getUsers().size(), 3);

    }
    @Test
    public void createExpenseWithEqualSplit() {
        val user1 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        val user2 = registrationController.register("user2", "user2@gmail.com", 3743847880L);
        val user3 = registrationController.register("user3", "user3@gmail.com", 3743847880L);
        val group1 = groupManagerController.createGroup("group1");
        groupManagerController.addUser(group1, user1);
        groupManagerController.addUser(group1, user2);
        groupManagerController.addUser(group1, user3);

        val split = Split.builder()
                .owedTo(user1)
                .amount(3000.00)
                .group(group1)
                .build();
        val expense = expenseManagerController.createExpense(split);
        Assertions.assertEquals(expense.getAmount(), 3000.00);
        Assertions.assertEquals(expense.getBalances().get(user2).getAmount(), 1000.00);
        Assertions.assertEquals(expense.getBalances().get(user3).getAmount(), 1000.00);
        Assertions.assertEquals(user1.getTotalOwed(), 2000.00);

    }

    @Test
    public void createExpenseWithExactSplit() {
        val user1 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        val user2 = registrationController.register("user2", "user2@gmail.com", 3743847880L);
        val user3 = registrationController.register("user3", "user3@gmail.com", 3743847880L);
        val group1 = groupManagerController.createGroup("group1");
        groupManagerController.addUser(group1, user1);
        groupManagerController.addUser(group1, user2);
        groupManagerController.addUser(group1, user3);

        val split = Split.builder()
                .owedTo(user1)
                .amount(3000.00)
                .group(group1)
                .splits(Map.of(
                        user1, 700.00,
                        user2, 1000.00,
                        user3, 1300.00
                ))
                .splitMode(SplitModeEnum.EXACT)
                .build();
        val expense = expenseManagerController.createExpense(split);
        Assertions.assertEquals(expense.getAmount(), 3000.00);
        Assertions.assertEquals(expense.getBalances().get(user2).getAmount(), 1000.00);
        Assertions.assertEquals(expense.getBalances().get(user3).getAmount(), 1300.00);
        Assertions.assertEquals(user1.getTotalOwed(), 2300.00);

    }

    @Test
    public void settleBalances() {
        val user1 = registrationController.register("user1", "user1@gmail.com", 3743847880L);
        val user2 = registrationController.register("user2", "user2@gmail.com", 3743847880L);
        val user3 = registrationController.register("user3", "user3@gmail.com", 3743847880L);
        val group1 = groupManagerController.createGroup("group1");
        groupManagerController.addUser(group1, user1);
        groupManagerController.addUser(group1, user2);
        groupManagerController.addUser(group1, user3);

        val split = Split.builder()
                .owedTo(user1)
                .amount(3000.00)
                .group(group1)
                .splits(Map.of(
                        user1, 700.00,
                        user2, 1000.00,
                        user3, 1300.00
                ))
                .splitMode(SplitModeEnum.EXACT)
                .build();
        val expense = expenseManagerController.createExpense(split);
        val balance2 = expense.getBalances().get(user2);
        val balance3 = expense.getBalances().get(user3);
        var status1 = balanceSettlementController.settleBalance(expense, balance2, 500.00);
        Assertions.assertEquals(status1, Boolean.FALSE);
        Assertions.assertEquals(expense.getIsSettled(), Boolean.FALSE);
        status1 = balanceSettlementController.settleBalance(expense, balance2, 500.00);
        Assertions.assertEquals(status1, Boolean.TRUE);
        Assertions.assertEquals(expense.getIsSettled(), Boolean.FALSE);
        var status2 = balanceSettlementController.settleBalance(expense, balance3, 1300.00);
        Assertions.assertEquals(status2, Boolean.TRUE);
        Assertions.assertEquals(expense.getIsSettled(), Boolean.TRUE);

        Assertions.assertEquals(user1.getTotalOwed(), 0.00);
        Assertions.assertEquals(user2.getTotalOwe(), 0.00);
        Assertions.assertEquals(user3.getTotalOwe(), 0.00);

    }
}
