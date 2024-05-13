package com.example.DocumentProject.exceptions;

public class ExecutionRestMethodException extends Exception{
    public ExecutionRestMethodException(String message) {
        super(message);
    }

    public ExecutionRestMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
