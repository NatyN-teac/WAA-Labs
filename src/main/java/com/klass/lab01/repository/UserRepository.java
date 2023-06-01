package com.klass.lab01.repository;

import com.klass.lab01.domain.Post;
import com.klass.lab01.domain.User;
import com.klass.lab01.dto.request.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :id")
    List<Post> findUserPostsById(@Param("id") long id);

    @Query("SELECT u FROM User u JOIN u.posts p GROUP BY u.id HAVING COUNT(p) > 1")
    List<User> findUsersByPostsCount();

}
