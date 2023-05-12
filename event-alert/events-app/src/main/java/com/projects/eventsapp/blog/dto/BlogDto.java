package com.projects.eventsapp.blog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.CustomValidation.BlogCategoryValidation;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class BlogDto {

    @JsonView({JsonViewProfiles.BlogPostDetail.class})
    private Long id;
    @NotBlank(message = "please provide a title")
    @JsonView({JsonViewProfiles.BlogPostChannel.class, JsonViewProfiles.BlogPostDetail.class})
    private String title;
    @NotBlank(message = "please provide some content")
    @JsonView({JsonViewProfiles.BlogPostChannel.class, JsonViewProfiles.BlogPostDetail.class})
    private String text;
    @BlogCategoryValidation()
    @JsonView({JsonViewProfiles.BlogPostDetail.class, JsonViewProfiles.BlogPostChannel.class})
    private String category;
    @JsonView({JsonViewProfiles.BlogPostDetail.class, JsonViewProfiles.BlogPostChannel.class})
    private int likesCount;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView({JsonViewProfiles.BlogPostDetail.class, JsonViewProfiles.BlogPostChannel.class})
    private Date publicationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView({JsonViewProfiles.BlogPostDetail.class})
    private Date updateDate;
    @JsonView({JsonViewProfiles.BlogPostChannel.class, JsonViewProfiles.BlogPostDetail.class})
    private UserDto user;
    @JsonView(JsonViewProfiles.BlogPostDetail.class)
    private Set<User> likes;
    @NotNull(message = "please provide area")
    @JsonView({JsonViewProfiles.BlogPostDetail.class, JsonViewProfiles.BlogPostChannel.class})
    private Integer area;



    public BlogDto() {};

    public BlogDto(BlogPost blogPost) {
        this.id = blogPost.getId();
        this.title = blogPost.getTitle();
        this.text = blogPost.getText();
        this.category = blogPost.getCategory();
        this.likesCount = blogPost.getLikesCount();
        this.publicationDate = blogPost.getPublicationDate();
        this.updateDate = blogPost.getUpdateDate();
        this.user = new UserDto(blogPost.getUser());
        this.likes = blogPost.getLikes();
        this.area = blogPost.getArea();
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getCategory () {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getArea() {
        return area;
    }
    public void setArea(Integer area) {
        this.area = area;
    }

    public int getLikesCount() {
        return likesCount;
    }
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public UserDto getUserDto() {
        return user;
    }
    public void setUserDto(User user) {
        this.user = new UserDto(user);
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

}
