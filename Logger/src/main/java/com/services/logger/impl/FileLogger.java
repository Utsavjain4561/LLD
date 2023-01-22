package com.services.logger.impl;

public class FileLogger implements ILogger {
    public static final String FILE_WRITE = "FILE_WRITE:";

    @Override
    public void write(String message) {
        // Writes the message to a file
        System.out.println(FILE_WRITE + " " + message);
    }
}
