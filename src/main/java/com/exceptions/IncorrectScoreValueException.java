package com.exceptions;

public class IncorrectScoreValueException extends RuntimeException {
    private static final String MESSAGE = "Incorrect score value. Must be grater than 0";

    public IncorrectScoreValueException() {
        super(MESSAGE);
    }
}
