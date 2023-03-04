package com.projects.eventsapp.errorHandling;

public class AlreadyFollowingUserException extends Exception {
    public AlreadyFollowingUserException(String error) {
        super(error);
    }

}
