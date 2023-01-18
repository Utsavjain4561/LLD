package org.splitwise.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Balance {
    private final User owedBy;
    private final User owedTo;
    private final Double amount;
    @Builder.Default
    private final Boolean isSettled = Boolean.FALSE;
}
