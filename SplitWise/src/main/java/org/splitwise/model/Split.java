package org.splitwise.model;

import lombok.Builder;
import lombok.Getter;
import org.splitwise.enums.SplitModeEnum;

import java.util.Map;

@Builder
@Getter
public class Split {
    private final Double amount;
    @Builder.Default
    private final SplitModeEnum splitMode = SplitModeEnum.EQUAL;
    private final Map<User, Double> splits;
    private final Group group;
    private final User owedTo;
}
