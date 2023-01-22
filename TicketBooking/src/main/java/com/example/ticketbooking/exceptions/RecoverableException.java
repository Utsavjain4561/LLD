package com.example.ticketbooking.exceptions;

public class RecoverableException extends Exception{
    public RecoverableException(final String message) throws Exception {
        throw new Exception(message);
    }
}
