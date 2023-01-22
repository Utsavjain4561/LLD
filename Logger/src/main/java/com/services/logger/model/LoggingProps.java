package com.services.logger.model;

import com.services.logger.enums.LogLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoggingProps {
    private final LogLevel logLevel;

}
