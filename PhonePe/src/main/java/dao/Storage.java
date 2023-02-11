package dao;

import models.Problem;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Storage {

    private static List<Problem> problems;
    private static List<User> leaderboard;

    private Storage() {}

    public static List<Problem> getProblems() {
        if(Objects.isNull(problems)){
            problems = new ArrayList<>();
        }
        return problems;
    }
    public static List<User> getLeaderboard() {
        if(Objects.isNull(leaderboard)){
            leaderboard = new ArrayList<>();
        }
        leaderboard.sort((user, t1) -> t1.getScore() - user.getScore());
        return leaderboard;
    }
    public static void addUser(User user){
        if(Objects.isNull(leaderboard)){
            leaderboard = new ArrayList<>();
        }
        leaderboard.add(user);
    }
}
