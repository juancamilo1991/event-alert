package com.projects.eventsapp.user.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.errorHandling.AlreadyFollowingUserException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @JsonView({JsonViewProfiles.UserDetails.class, JsonViewProfiles.BlogPostDetail.class})
    private String username;
    @Column(unique = true)
    private String email;
    private String story;
    private String password;
    private Popularity popularity = Popularity.LOW;

    @ManyToMany
    @JoinTable(
            name = "blogPosts_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_post_id")
    )
    private Set<BlogPost> likedBlogPosts;

    @ManyToMany
    @JoinTable(
            name = "following_relations",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<User> following = new HashSet<>();


    public User() {};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public void addLikedBlogPosts(BlogPost blogPost) {
        likedBlogPosts.add(blogPost);
    }

    public Popularity getPopularity() {
        return popularity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
