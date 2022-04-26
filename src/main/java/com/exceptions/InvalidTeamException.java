package com.exceptions;

public class InvalidTeamException extends RuntimeException {
    public static final String MESSAGE = "The same countries can't play against theirself and can't be empty";

    public InvalidTeamException() {
        super(MESSAGE);
    }
}
