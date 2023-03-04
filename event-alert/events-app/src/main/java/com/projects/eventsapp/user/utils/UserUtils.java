package com.projects.eventsapp.user.utils;
import com.projects.eventsapp.blog.service.BlogPostService;
import com.projects.eventsapp.errorHandling.AlreadyFollowingUserException;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.Popularity;
import com.projects.eventsapp.user.model.User;
import com.projects.eventsapp.user.service.UserDetailsServiceImpl;
import com.projects.eventsapp.user.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtils {

    private final UserDetailsServiceImpl userDetailsService;
    private final BlogPostService blogPostService;
    private final UserService userService;

    public UserUtils(UserDetailsServiceImpl userDetailsService, BlogPostService blogPostService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.blogPostService = blogPostService;
        this.userService = userService;
    }

    public static List<UserDto> mapToUserDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
    }

    public User getStoredUser (Principal principal) throws UsernameNotFoundException {
            return userDetailsService.getUserByUsername(principal);
    }

    public Popularity getPopularity(Long id) {
        return userService.getUserById(id).getPopularity();
    }

    public void setPopularity(Long id) {
        User user = userService.getUserById(id);
        int likesSum = blogPostService.getAllPosts(id).stream()
                .reduce(0,
                        (partialResult, currentPost) -> partialResult + currentPost.getLikesCount(),
                        Integer::sum);

        if (likesSum <= Popularity.LOW.getLevelOfPop()) {
            user.setPopularity(Popularity.LOW);
        }
        if (likesSum > Popularity.LOW.getLevelOfPop() && likesSum <= Popularity.MODERATE.getLevelOfPop()) {
            user.setPopularity(Popularity.MODERATE);
        }
        else {
            user.setPopularity(Popularity.HIGH);
        }
        userService.updateUser(user);
    }

    public void addFollower(User follower, User followed) throws AlreadyFollowingUserException {
        if (userService.isAlreadyFollowing(follower.getId(), followed.getId()).isPresent()) {
            throw new AlreadyFollowingUserException("you are already following " + followed.getUsername());
        }
        follower.getFollowing().add(followed);
        followed.getFollowers().add(follower);
    }

}
