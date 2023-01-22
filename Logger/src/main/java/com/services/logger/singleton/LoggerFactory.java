package com.services.logger.singleton;

import com.services.logger.enums.LoggerType;
import com.services.logger.impl.ILogger;

import java.io.Serializable;
import java.util.Objects;

// How to prevent multiple creation of objects while serialization
// Lazy init
// Normal init
// Sync of getLogger() method
// Double-checking impl of singleton pattern
public class LoggerFactory implements Serializable {
    private static ILogger logger;
    private LoggerFactory() {}

    public static ILogger getLogger(final LoggerType loggerType) {
        if(Objects.isNull(logger)){
            logger = loggerType.getConstructor().get();
        }
        return logger;
    }


}
