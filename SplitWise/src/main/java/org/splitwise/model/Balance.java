package org.splitwise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Balance {
    private final User owedBy;
    private final User owedTo;
    @Setter
    private Double amount;
    @Builder.Default
    @Setter
    private Boolean isSettled = Boolean.FALSE;
}
