package org.code.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Counter {
    private final String color;
    private final Player player;
    @Builder.Default
    @Setter
    private Integer position = 0;
}
