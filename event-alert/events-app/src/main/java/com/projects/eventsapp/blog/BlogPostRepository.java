package com.projects.eventsapp.blog;

import com.projects.eventsapp.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    // all posts of one area ordered by newest (view for the current blog channel)
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.area = :area ORDER BY bp.publication_date ASC",
            nativeQuery = true)
    List<BlogPost> findByAreaOrderByPublicationDate(@Param("area") Integer area);

    // all posts of one area and specific category ordered by newest
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.area = :area AND bp.category = :category ORDER BY bp.publication_date DESC",
            nativeQuery = true)
    List<BlogPost> findByAreaAndCategoryOrderByPublicationDateDesc(@Param("area") Integer area,
                                                                   @Param("category") String category);

    // all posts of one area and specific category ordered by oldest
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.area = :area AND bp.category = :category ORDER BY bp.publication_date ASC",
            nativeQuery = true)
    List<BlogPost> findByAreaAndCategoryOrderByPublicationDateAsc(@Param("area") Integer area,
                                                                  @Param("category") String category);

    // all posts of one area and specific category ordered by most liked
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.area = :area AND bp.category = :category ORDER BY bp.likes_count DESC",
            nativeQuery = true)
    List<BlogPost> findByAreaAndCategoryOrderByLikesCountDesc(@Param("area") Integer area,
                                                              @Param("category") String category);

    // all posts of one area and specific category ordered by less liked
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.area = :area AND bp.category = :category ORDER BY bp.likes_count ASC",
            nativeQuery = true)
    List<BlogPost> findByAreaAndCategoryOrderByLikesCountAsc(@Param("area") Integer area,
                                                             @Param("category") String category);

    // all posts of a specific user ordered by newest
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.user_id = ?1 ORDER BY bp.publication_date DESC",
            nativeQuery = true)
    List<BlogPost> findByUserIdOrderByPublicationDateDesc (Long id);

    // all posts of a specific user ordered by oldest
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.user_id = ?1 ORDER BY bp.publication_date ASC",
            nativeQuery = true)
    List<BlogPost> findByUserIdOrderByPublicationDateAsc (Long id);

    // all posts of a specific user ordered by most liked
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.user_id = ?1 ORDER BY bp.likes_count DESC",
            nativeQuery = true)
    List<BlogPost> findByUserIdOrderByLikesCountDesc (Long id);

    // all posts of a specific user ordered by less liked
    @Query(value = "SELECT * FROM blog_posts bp WHERE bp.user_id = ?1 ORDER BY bp.likes_count ASC",
            nativeQuery = true)
    List<BlogPost> findByUserIdOrderByLikesCountAsc (Long id);

    List<BlogPost> findByUserId (Long id);

    void deleteById (Long id);

}
