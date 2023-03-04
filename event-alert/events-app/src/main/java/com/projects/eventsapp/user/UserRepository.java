package com.projects.eventsapp.user;

import com.projects.eventsapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM blog_posts_likes bpl INNER JOIN users u ON bpl.user_id = u.id AND u.id = :userId AND bpl.blog_post_id = :postId",
            nativeQuery = true)
    Optional<User> findByAlreadyLiked(@Param("userId") Long userId,
                                      @Param("postId") Long postId);

    @Query(value = "SELECT * FROM following_relations fr INNER JOIN users u ON u.id = fr.follower_id AND u.id = :followerId AND fr.followed_id = :followingId",
            nativeQuery = true)
    Optional<User> findByAlreadyFollowing(@Param("followerId") Long followerId,
                                        @Param("followingId") Long followingId);

}
