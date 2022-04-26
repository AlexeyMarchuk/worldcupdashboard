package com.exceptions;

public class TeamAlreadyPlayingException extends RuntimeException {
    public static final String MESSAGE = "One of the team already playing";

    public TeamAlreadyPlayingException() {
        super(MESSAGE);
    }
}
