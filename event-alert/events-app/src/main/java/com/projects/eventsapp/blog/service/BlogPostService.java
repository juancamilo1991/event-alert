package com.projects.eventsapp.blog.service;

import com.projects.eventsapp.blog.BlogPostRepository;
import com.projects.eventsapp.blog.dto.BlogDto;
import com.projects.eventsapp.blog.model.BlogPost;
import com.projects.eventsapp.blog.utils.EmptyBlogPostGuardImpl;
import com.projects.eventsapp.errorHandling.BlogCreationFailedException;
import com.projects.eventsapp.errorHandling.NoSuchBlogEntryException;
import com.projects.eventsapp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {


    @Autowired
    BlogPostRepository blogPostRepository;
    EmptyBlogPostGuardImpl emptyBlogPostGuard = new EmptyBlogPostGuardImpl();

    public BlogPost createPost(User user, BlogDto blogDto) throws BlogCreationFailedException{
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogDto.getTitle());
        blogPost.setText(blogDto.getText());
        blogPost.setUser(user);
        blogPost.setCategory(blogDto.getCategory());
        blogPost.setArea(blogDto.getArea());
        if (blogPostRepository.save(blogPost) == null) {
            throw new BlogCreationFailedException("Beitrag konnte nicht erstellt werden!");
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

    public List<BlogPost> getByAreaOrderByNewest (Integer area) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByAreaOrderByPublicationDate(area);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);

    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

     public List<BlogPost> getByAreaCategoryOrderByLessLiked(Integer area, String category) throws NoSuchBlogEntryException {
         List<BlogPost> result = blogPostRepository.findByAreaAndCategoryOrderByLikesCountDesc(area, category);
         return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
     }

    public List<BlogPost> getByAreaCategoryOrderByMostLiked(Integer area, String category) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByAreaAndCategoryOrderByLikesCountAsc(area, category);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);

    }

    public List<BlogPost> getByAreaCategoryOrderByOldest(Integer area, String category) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByAreaAndCategoryOrderByPublicationDateDesc(area, category);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
    }

    public List<BlogPost> getByAreaCategoryOrderByNewest(Integer area, String category) throws NoSuchBlogEntryException {
           List<BlogPost> result = blogPostRepository.findByAreaAndCategoryOrderByPublicationDateAsc(area, category);
           return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
    }

    public List<BlogPost> getByUserOrderByNewest(Long id) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByUserIdOrderByPublicationDateDesc(id);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
    }

    public List<BlogPost> getByUserOrderByOldest(Long id) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByUserIdOrderByPublicationDateAsc(id);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
    }

    public List<BlogPost> getByUserOrderByMostLiked(Long id) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByUserIdOrderByLikesCountDesc(id);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);

    }

    public List<BlogPost> getByUserOrderByLessLiked(Long id) throws NoSuchBlogEntryException {
        List<BlogPost> result = blogPostRepository.findByUserIdOrderByLikesCountAsc(id);
        return emptyBlogPostGuard.checkEmptyBlogPostResult(result);
    }

    public void updatePost(BlogPost post) {
        blogPostRepository.save(post);
    }


}