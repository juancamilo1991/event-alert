package com.projects.eventsapp.blog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.dto.BlogDto;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.blog.service.BlogPostService;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.blog.utils.BlogUtils;
import com.projects.eventsapp.errorHandling.BlogCreationFailedException;
import com.projects.eventsapp.errorHandling.NoSuchBlogEntryException;
import com.projects.eventsapp.errorHandling.UserAlreadyExistException;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;
import com.projects.eventsapp.user.service.UserDetailsServiceImpl;
import com.projects.eventsapp.user.service.UserService;
import com.projects.eventsapp.user.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/v1/blog")
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final UserService userService;
    private final UserUtils userUtils;


    public BlogPostController(BlogPostService blogPostService, UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.blogPostService = blogPostService;
        this.userService = userService;
        this.userUtils = new UserUtils(userDetailsService, blogPostService, userService);
    }

    // locked
    @PostMapping(path = "/create-post")
    @JsonView(JsonViewProfiles.BlogPostChannel.class)
    public ResponseEntity<?> createPost(@NotNull Principal principal, @RequestBody @Valid BlogDto blogDto) {
        try {
            BlogDto newBlogDto = BlogUtils.mapToDto((Collections.singletonList(blogPostService.createPost(userService.getStoredUser(principal), blogDto)))).get(0);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBlogDto);
        } catch (UsernameNotFoundException | BlogCreationFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // locked
    @DeleteMapping(path = "/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "id") Long postId, Principal principal) {
        try {
            userService.getStoredUser(principal);
            blogPostService.deletePost(postId);
            return ResponseEntity.ok("Post successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post could not be deleted");
        }
    }

    @GetMapping(path = "/channel-posts")
    @JsonView(JsonViewProfiles.BlogPostChannel.class)
    public ResponseEntity<?> getAllPosts(@RequestParam Integer area) {
        try {
            return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByAreaOrderByNewest(area)));
        } catch (NoSuchBlogEntryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/posts")
    @JsonView(JsonViewProfiles.BlogPostChannel.class)
    public ResponseEntity<?> getPostsByCategory(@RequestParam Integer area,
                                            @RequestParam String category,
                                            @RequestParam(required = false, defaultValue = "newest") String order) {
        try {
            if (order.equals("newest")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByAreaCategoryOrderByNewest(area, category)));
            }
            if (order.equals("oldest")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByAreaCategoryOrderByOldest(area, category)));
            }
            if (order.equals("most")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByAreaCategoryOrderByMostLiked(area, category)));
            }
            return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByAreaCategoryOrderByLessLiked(area, category)));
        }
        catch (NoSuchBlogEntryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{postId}")
    @JsonView(JsonViewProfiles.BlogPostDetail.class)
    public BlogDto getBlogPost(@PathVariable Long postId) {
        return new BlogDto(getPostById(postId));
    }

    // locked
    @GetMapping(path = "/my-posts")
    @JsonView(JsonViewProfiles.BlogPostChannel.class)
    public ResponseEntity<?> getMyPosts(Principal principal,
                                    @RequestParam(required = false, defaultValue = "newest") String order) {
        try {
            if (order.equals("newest")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByUserOrderByNewest(userService.getStoredUser(principal).getId())));
            }

            if (order.equals("oldest")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByUserOrderByOldest(userService.getStoredUser(principal).getId())));
            }

            if (order.equals("most")) {
                return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByUserOrderByMostLiked(userService.getStoredUser(principal).getId())));
            }
            return ResponseEntity.ok(BlogUtils.mapToDto(blogPostService.getByUserOrderByLessLiked(userService.getStoredUser(principal).getId())));
        }
        catch (NoSuchBlogEntryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // locked
    @PutMapping(path = "/add-like/{id}")
    public ResponseEntity<String> addLike(Principal principal, @PathVariable Long id) {
        User user = userService.getStoredUser(principal);
        BlogPost post = getPostById(id);
        // check if already liked this post
        if (userService.isAlreadyLiked(user.getId(), post.getId()).isPresent()) {
            return ResponseEntity.ok("Already liked!");
        } else {
            post.addLikes(user);
            user.addLikedBlogPosts(post);
            post.incrementLikeCount();

            blogPostService.updatePost(post);

            userUtils.setPopularity(post.getUser().getId());

            return ResponseEntity.ok("Like was successfully added");
        }
    }

    private BlogPost getPostById(Long id) throws NoSuchElementException {
        return blogPostService.getPostById(id).orElseThrow(() -> new NoSuchElementException("post not found"));
    }

}
