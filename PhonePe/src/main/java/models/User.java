package models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@Slf4j
public class User {
    private final String name;
    private final String department;
    @Setter
    private Integer score = 0;
    @Setter
    private List<Problem> problemList  = new ArrayList<>();

    public void addToSolved(@NonNull final Problem problem){
        log.info("Problem : {} is solved by user : {}", problem, this);
        problemList.add(problem);
    }

}
