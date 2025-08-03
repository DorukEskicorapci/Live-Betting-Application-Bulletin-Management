package com.example.livebetting.exceptionHandler;



public class MissingArgumentException extends RuntimeException {

    public MissingArgumentException(String message) {
        super(message);
    }
}
