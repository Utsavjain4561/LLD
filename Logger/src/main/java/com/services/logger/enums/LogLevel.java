package com.services.logger.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LogLevel {
    ERROR(1, "ERROR"),
    WARNING(2, "WARNING"),
    INFO(3, "INFO"),
    DEBUG(4, "DEBUG");
    private final Integer level;
    private final String prefix;
}
