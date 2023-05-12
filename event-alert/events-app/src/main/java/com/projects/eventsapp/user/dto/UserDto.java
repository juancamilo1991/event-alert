package com.projects.eventsapp.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.user.model.Popularity;
import com.projects.eventsapp.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class UserDto extends LoginDto {

    @JsonView(JsonViewProfiles.UserDetails.class)
    private Long id;
    @NotBlank(message = "email must not be empty")
    @JsonView(JsonViewProfiles.UserDetails.class)
    private String email;
    @JsonView(JsonViewProfiles.UserDetails.class)
    private String story;
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Set<BlogPost> likedBlogPosts;
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Set<User> followers;
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Set<User> following;
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Popularity popularity;



    public UserDto() {};

    public UserDto(User user) {
        this.id = user.getId();
        this.setUsername(user.getUsername());
        this.email = user.getEmail();
        this.story = user.getStory();
        this.likedBlogPosts = user.getLikedBlogPosts();
        this.followers = user.getFollowers();
        this.following = user.getFollowing();
        this.popularity = user.getPopularity();
        this.story = user.getStory();
    }

}
