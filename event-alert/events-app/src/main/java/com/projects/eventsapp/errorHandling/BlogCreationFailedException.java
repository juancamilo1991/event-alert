package com.projects.eventsapp.errorHandling;

public class BlogCreationFailedException extends Exception {
    public BlogCreationFailedException(String error) {
        super(error);
    }
}
