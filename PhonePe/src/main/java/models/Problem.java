package models;

import enums.Level;
import enums.Tag;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Slf4j
public class Problem {

    private final String name;
    private final ProblemProps props;

    @Setter
    private Map<String, User> contestantMap = new HashMap<>();
    @Setter
    private Double avgTime=0.0d;

    @Builder
    @Getter
    public static class ProblemProps{
        private final String description;
        private final Level level;
        private final Set<Tag> tags;
        private final Integer score;
    }

    private void calculateAvgTime(final Long timeTaken){
        val users = contestantMap.size();
        var currentAvg = this.getAvgTime();
        currentAvg = (currentAvg*users + timeTaken)/(users+1);
        this.setAvgTime(currentAvg);
    }
    public void addUserToSolvedList(@NonNull final User user, @NonNull final Long timeTaken){
        log.info("Adding user: {} to solved list of this problem: {}", user.toString(), this);
        contestantMap.putIfAbsent(user.getName(), user);
        calculateAvgTime(timeTaken);
    }

}
