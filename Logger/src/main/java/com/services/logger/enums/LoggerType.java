package com.services.logger.enums;

import com.services.logger.impl.ILogger;
import com.services.logger.impl.FileLogger;
import com.services.logger.impl.SystemLogger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public enum LoggerType {
    SYSTEM_LOGGER(SystemLogger::new),
    FILE_LOGGER(FileLogger::new);
    private final Supplier<ILogger> constructor;
}
