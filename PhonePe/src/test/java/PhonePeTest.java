import enums.Level;
import enums.Tag;
import lombok.val;
import models.Problem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LeaderBoardManagerService;
import services.ProblemsManagerService;
import services.RegistrationService;
import startegy.FullScoreStrategy;

import java.util.Set;

public class PhonePeTest {
    private RegistrationService registrationService;
    private ProblemsManagerService problemsManagerService;
    private LeaderBoardManagerService leaderBoardManagerService;
    @BeforeEach
    void setup(){
        val fullScoreStrategy = new FullScoreStrategy();
        registrationService = new RegistrationService();
        problemsManagerService = new ProblemsManagerService(fullScoreStrategy);
        leaderBoardManagerService = new LeaderBoardManagerService();

    }

    @Test
    void testCase(){
        val user1 = registrationService.addUser("user1", "department1");
        val user2 = registrationService.addUser("user2", "department1");
        val user3 = registrationService.addUser("user3", "department2");
        val user4 = registrationService.addUser("user4", "department2");
        val user5 = registrationService.addUser("user5", "department3");
        val user6 = registrationService.addUser("user6", "department4");

        // create a problem
        val problem1 = problemsManagerService.addProblem("Create linked list",
                "Create a linked list using two pointers", Set.of(Tag.LINKED_LIST), Level.EASY,
                50);
        val problem2 = problemsManagerService.addProblem("Coin change",
                "This problem is related to coin change", Set.of(Tag.DP, Tag.ARRAY), Level.MEDIUM,
                100);
        val problem3 = problemsManagerService.addProblem("Shortest path in graph",
                "This problem is related to shortest path", Set.of(Tag.GRAPH, Tag.ARRAY, Tag.TREE), Level.HARD,
                150);
        val problem4 = problemsManagerService.addProblem("Create double linked list",
                "Create a linked list using two pointers", Set.of(Tag.LINKED_LIST), Level.EASY,
                50);

        val result = problemsManagerService.fetchProblems(Level.MEDIUM, Set.of(Tag.ARRAY, Tag.DP));

        problemsManagerService.solve(user1, problem1);
        problemsManagerService.solve(user2, problem1);
        problemsManagerService.solve(user3, problem1);
        problemsManagerService.solve(user4, problem1);
        problemsManagerService.solve(user5, problem2);
        problemsManagerService.solve(user1, problem2);
        problemsManagerService.solve(user2, problem3);
        problemsManagerService.solve(user6, problem3);
        problemsManagerService.solve(user6, problem1);
        problemsManagerService.solve(user6, problem2);
        problemsManagerService.solve(user6, problem4);

        problemsManagerService.fetchProblemsSolved(user1);
        val leader  = leaderBoardManagerService.getCurrentLeader();
        leaderBoardManagerService.getUsersSolvedProblem(problem1);
        leaderBoardManagerService.getUsersSolvedProblem(problem2);

        problemsManagerService.getTopNProblems(Tag.DP, 2);
        problemsManagerService.getTopNProblems(Tag.LINKED_LIST, 2);

    }
}
