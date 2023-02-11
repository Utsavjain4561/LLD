package startegy;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Problem;
import models.User;
import services.ISolver;

import java.util.List;

@Slf4j
public class FullScoreStrategy implements ISolver {
    private final List<Boolean> resultList = List.of(true, false);
    @Override
    public Boolean solve(@NonNull User user, @NonNull Problem problem) {
        // it gives full score of the problem to the user if problem is solved
        val result = resultList.get((int)(Math.random()%2));
        if(result){
            user.addToSolved(problem);
            val previousScore = user.getScore();
            user.setScore(previousScore +  problem.getProps().getScore());
            return true;

        }else{
            log.info("User :{} was not able to solve the problem:{}", user.toString(), problem.toString());
            return false;
        }
    }
}
