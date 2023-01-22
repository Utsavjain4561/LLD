package com.services.logger.impl;

import com.services.logger.enums.LogLevel;
import com.services.logger.model.LoggingProps;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class LoggerImpl implements ILevelLogger {
    private final ILogger logger;
    private final LoggingProps loggingProps;

    @Override
    public void info(String message) {
        if (isLevelEnabled(LogLevel.INFO)) {
            display(message, LogLevel.INFO.getPrefix());
        }
    }

    @Override
    public void debug(String message) {
        if (isLevelEnabled(LogLevel.DEBUG)) {
            display(message, LogLevel.DEBUG.getPrefix());
        }
    }

    @Override
    public void warning(String message) {
        if (isLevelEnabled(LogLevel.WARNING)) {
            display(message, LogLevel.WARNING.getPrefix());
        }
    }

    @Override
    public void error(String message) {
        if (isLevelEnabled(LogLevel.ERROR)) {
            display(message, LogLevel.ERROR.getPrefix());
        }
    }

    private Boolean isLevelEnabled(final LogLevel currentLogLevel) {
        return currentLogLevel.getLevel().compareTo(loggingProps.getLogLevel().getLevel()) <= 0;
    }

    private void display(final String message, final String logPrefix) {
        // Use the type of logger given above and log the message
        val formattedMessage = "[" + logPrefix + "]" + " " + message;
        logger.write(formattedMessage);
    }
}
