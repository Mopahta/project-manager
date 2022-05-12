package com.mopahta.projectmanager.exception;

public class InvalidValuesException extends Exception{
    public InvalidValuesException() {
    }

    public InvalidValuesException(String message) {
        super(message);
    }

    public InvalidValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
