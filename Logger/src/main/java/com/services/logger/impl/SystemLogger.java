package com.services.logger.impl;

public class SystemLogger implements ILogger {
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
