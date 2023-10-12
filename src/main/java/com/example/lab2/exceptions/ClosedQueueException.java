package com.example.lab2.exceptions;

public class ClosedQueueException extends Exception{
    public ClosedQueueException(String message) {
        super(message);
    }
}
