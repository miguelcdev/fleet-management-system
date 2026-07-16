package com.empresa.transporte.exception;

public class DuplicatePlateException extends RuntimeException{
    public DuplicatePlateException(String message) {
        super(message);
    }
}
