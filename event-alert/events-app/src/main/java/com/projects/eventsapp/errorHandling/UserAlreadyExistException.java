package com.projects.eventsapp.errorHandling;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String error) {
        super(error);
    }
}
