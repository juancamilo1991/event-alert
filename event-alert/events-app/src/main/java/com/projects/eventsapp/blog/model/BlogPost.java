package com.projects.eventsapp.blog.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "blog_posts")
public class BlogPost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Long id;
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostPreview.class})
    private String title;
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostPreview.class})
    private String text;
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostPreview.class})
    private String category;
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostPreview.class})
    private int likesCount;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostPreview.class})
    private Date publicationDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Date updateDate = new Date();
    @JsonView(JsonViewProfiles.BlogPostDetail.class)
    private Integer area;
    @ManyToOne
    @JsonView(JsonViewProfiles.UserDetails.class)
    private User user;
    @ManyToMany(mappedBy = "likedBlogPosts")
    @JsonView(JsonViewProfiles.UserDetails.class)
    private Set<User> likes;

    public BlogPost() {}

    public BlogPost(String title, String text) {
        this.title = title;
        this.text = text;
        this.likesCount = 0;
    }

    public void incrementLikeCount() {
        //get likes from db
        likesCount += 1;
    }

    public void addLikes(User user) {
        likes.add(user);
    }

}
