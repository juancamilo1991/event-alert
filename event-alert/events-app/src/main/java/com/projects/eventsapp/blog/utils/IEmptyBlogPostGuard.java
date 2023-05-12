package com.projects.eventsapp.blog.utils;

import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.errorHandling.NoSuchBlogEntryException;

import java.util.EventListener;
import java.util.List;

public interface IEmptyBlogPostGuard {

    List<BlogPost> checkEmptyBlogPostResult(List<BlogPost> result) throws NoSuchBlogEntryException;

}
