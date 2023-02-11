package services;

import dao.Storage;
import enums.Level;
import enums.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Problem;
import models.User;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ProblemsManagerService {
    private final ISolver solverStrategy;
    private final List<Problem> problemList = Storage.getProblems();

    public Problem addProblem(@NonNull final String name, @NonNull final String description,
                              @NonNull final Set<Tag> tags, @NonNull final Level level,
                              @NonNull final Integer score) {
        val props = Problem.ProblemProps.builder()
                .description(description)
                .level(level)
                .score(score)
                .tags(tags)
                .build();
        val problem = new Problem(name, props);
        problemList.add(problem);
        log.info("Problem added : {}", problem.toString());
        return problem;
    }

    public List<Problem> fetchProblems(@NonNull final Level level, @NonNull final Set<Tag> tags) {
        // filtering can be done on levels and tags
        return problemList.stream().filter(problem -> problem.getProps().getLevel().equals(level))
                .filter(problem -> problem.getProps().getTags().containsAll(tags))
                .sorted(Comparator.comparingInt(problem -> problem.getProps().getScore()))
                .peek(problem -> log.info("Problems matching criteria are {}", problem.toString()))
                .collect(Collectors.toList());

    }

    public void solve(@NonNull final User user, @NonNull final Problem problem) {
        var timeNow = Instant.now().toEpochMilli();
        if (solverStrategy.solve(user, problem)) {
            timeNow = Instant.now().toEpochMilli() - timeNow;
            problem.addUserToSolvedList(user, timeNow);
        }
    }

    public List<Problem> fetchProblemsSolved(@NonNull final User user) {
        val problems = user.getProblemList();
        log.info("Problems solved by the user :{} are {}", user.toString(), problems);
        return problems;
    }

    public List<Problem> getTopNProblems(@NonNull final Tag tag, @NonNull final Integer number) {
        // fetch problems of the tag and sort them based on the no if users solved
        val problems = problemList.stream()
                .filter(problem -> problem.getProps().getTags().contains(tag))
                .sorted((problem, t1) -> t1.getContestantMap().size() - problem.getContestantMap().size())
                .collect(Collectors.toList());
        // return top N problems
        List<Problem> topList;
        if (number > problems.size()) topList = problems;
        else {
            topList = problems.subList(0, number);
        }

        for (Problem problem : topList) {
            log.info("Problems in top N solved for tage:{} is :{} ", tag, problem.getName());
        }
        return topList;
    }
}
