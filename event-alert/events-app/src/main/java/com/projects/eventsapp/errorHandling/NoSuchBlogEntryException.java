package com.projects.eventsapp.errorHandling;

public class NoSuchBlogEntryException extends Exception {
    public NoSuchBlogEntryException(String error) {
        super(error);
    }
}
