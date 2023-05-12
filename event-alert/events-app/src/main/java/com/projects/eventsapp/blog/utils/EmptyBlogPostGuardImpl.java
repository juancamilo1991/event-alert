package com.projects.eventsapp.blog.utils;

import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.errorHandling.NoSuchBlogEntryException;

import java.util.List;

public class EmptyBlogPostGuardImpl implements IEmptyBlogPostGuard {

    @Override
    public List<BlogPost> checkEmptyBlogPostResult(List<BlogPost> result) throws NoSuchBlogEntryException {
        if (result.isEmpty()) {
            throw new NoSuchBlogEntryException("Kein Eintrag für diese Anfrage");
        }
        return result;
    }

}


