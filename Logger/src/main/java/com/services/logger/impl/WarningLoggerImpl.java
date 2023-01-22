package com.services.logger.impl;

import com.services.logger.enums.LogLevel;
import com.services.logger.model.LoggingProps;
import lombok.val;

public class WarningLoggerImpl implements IWarningLogger {
    private final ILogger logger;
    private final LoggingProps loggingProps;

    public WarningLoggerImpl(ILogger logger, LoggingProps loggingProps) {
        this.logger = logger;
        this.loggingProps = loggingProps;
    }
    @Override
    public void error(String message) {
        if (isLevelEnabled(LogLevel.ERROR)) {
            display(message, LogLevel.ERROR.getPrefix());
        }
    }

    @Override
    public void warning(String message) {
        if (isLevelEnabled(LogLevel.WARNING)) {
            display(message, LogLevel.WARNING.getPrefix());
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
