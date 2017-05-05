package com.dariuszpaluch.exception;

public class WrongDateFormatException  extends RuntimeException {

    public WrongDateFormatException() {
        super("Nieprawidłowa data");
    }
}
