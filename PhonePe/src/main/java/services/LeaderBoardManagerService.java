package services;

import dao.Storage;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Problem;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class LeaderBoardManagerService {

    public User getCurrentLeader() {
        val leader  = Storage.getLeaderboard().get(0);
        log.info("Current leader is :{} with score : {}", leader, leader.getScore());
        return leader;
    }

    public List<User> getUsersSolvedProblem (@NonNull final Problem problem){
        return  new ArrayList<>(problem.getContestantMap().values()).stream()
                .peek(user -> log.info("User:{} solved the problem:{}", user.getName(), problem.getName()))
                .collect(Collectors.toList());
    }
}
