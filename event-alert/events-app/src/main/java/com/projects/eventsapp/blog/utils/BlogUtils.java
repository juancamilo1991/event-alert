package com.projects.eventsapp.blog.utils;

import com.projects.eventsapp.blog.dto.BlogDto;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlogUtils {

    public static List<BlogDto> mapToDto(List<BlogPost> posts) {
        return posts.stream()
                .map(blogPost -> new BlogDto(blogPost))
                .collect(Collectors.toList());
    }

    public static List<BlogPost> sortPosts(String category,
                                    String zipCode,
                                    Function<String, List<BlogPost>> sortNoCategory,
                                    BiFunction<String, String, List<BlogPost>> sortWithCategory) {
        if (category.equals("empty")) {
            return sortNoCategory.apply(zipCode);
        }
        return sortWithCategory.apply(category, zipCode);
    }
}
