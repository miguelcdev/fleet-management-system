package com.empresa.transporte.exception;

public class InvalidTripDateException extends RuntimeException{
    public InvalidTripDateException(String message) {
        super(message);
    }
}
