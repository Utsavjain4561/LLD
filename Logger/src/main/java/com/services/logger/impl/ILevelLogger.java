package com.services.logger.impl;

public interface ILevelLogger {
    void info(final String message);
    void debug(final String message);
    void warning(final String message);
    void error(final String message);
}
