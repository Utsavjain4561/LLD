import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.splitwise.controllers.ExpenseManagerController;
import org.splitwise.controllers.GroupManagerController;
import org.splitwise.controllers.RegistrationController;
import org.splitwise.model.Split;
import org.splitwise.services.BalanceManagerService;
import org.splitwise.services.ExpenseManagerService;
import org.splitwise.services.GroupManagerService;
import org.splitwise.services.RegistrationService;

public class SplitWiseTest {

    private RegistrationController registrationController;
    private GroupManagerController groupManagerController;
    private ExpenseManagerController expenseManagerController;

    @BeforeEach
    public void setup() {
        val registrationService = new RegistrationService();
        val groupManagerService = new GroupManagerService();
        val expenseManagerService = new ExpenseManagerService();
        val balanceManagerService = new BalanceManagerService();
        this.registrationController = new RegistrationController(registrationService);
        this.groupManagerController = new GroupManagerController(groupManagerService);
        this.expenseManagerController = new ExpenseManagerController(expenseManagerService, balanceManagerService);

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
        Assertions.assertEquals(expense.getBalances().get(0).getOwedBy(), user1);
        Assertions.assertEquals(expense.getBalances().get(0).getAmount(), 1000.00);
        Assertions.assertEquals(expense.getBalances().get(1).getAmount(), 1000.00);
        Assertions.assertEquals(expense.getBalances().get(2).getAmount(), 1000.00);


    }
}
