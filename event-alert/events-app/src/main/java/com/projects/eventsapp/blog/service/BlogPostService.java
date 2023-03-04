package com.projects.eventsapp.blog.service;

import com.projects.eventsapp.blog.BlogPostRepository;
import com.projects.eventsapp.blog.dto.BlogDto;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.errorHandling.BlogCreationFailedException;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {


    @Autowired
    BlogPostRepository blogPostRepository;

    public BlogPost createPost(User user, BlogDto blogDto) throws BlogCreationFailedException{
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogDto.getTitle());
        blogPost.setText(blogDto.getText());
        blogPost.setUser(user);
        blogPost.setCategory(blogDto.getCategory());
        blogPost.setArea(blogDto.getArea());
        if (blogPostRepository.save(blogPost) == null) {
            throw new BlogCreationFailedException("Blogpost could not be created");
        }
        else {
            return blogPostRepository.save(blogPost);
        }
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public List<BlogPost> getAllPosts (Long id) {
        return blogPostRepository.findByUserId(id);
    }

    public List<BlogPost> getByAreaOrderByNewest (Integer area) {
        return blogPostRepository.findByAreaOrderByPublicationDate(area);
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

     public List<BlogPost> getByAreaCategoryOrderByLessLiked(Integer area, String category) {
        return blogPostRepository.findByAreaAndCategoryOrderByLikesCountAsc(area, category);
    }

    public List<BlogPost> getByAreaCategoryOrderByMostLiked(Integer area, String category) {
        return blogPostRepository.findByAreaAndCategoryOrderByLikesCountDesc(area, category);
    }

    public List<BlogPost> getByAreaCategoryOrderByOldest(Integer area, String category) {
        return blogPostRepository.findByAreaAndCategoryOrderByPublicationDateAsc(area, category);
    }

    public List<BlogPost> getByAreaCategoryOrderByNewest(Integer area, String category) {
        return blogPostRepository.findByAreaAndCategoryOrderByPublicationDateDesc(area, category);
    }

    public List<BlogPost> getByUserOrderByNewest(Long id) {
        return blogPostRepository.findByUserIdOrderByPublicationDateDesc(id);
    }

    public List<BlogPost> getByUserOrderByOldest(Long id) {
        return blogPostRepository.findByUserIdOrderByPublicationDateAsc(id);
    }

    public List<BlogPost> getByUserOrderByMostLiked(Long id) {
        return blogPostRepository.findByUserIdOrderByLikesCountDesc(id);
    }

    public List<BlogPost> getByUserOrderByLessLiked(Long id) {
        return blogPostRepository.findByUserIdOrderByLikesCountAsc(id);
    }

    public void updatePost(BlogPost post) {
        blogPostRepository.save(post);
    }


}