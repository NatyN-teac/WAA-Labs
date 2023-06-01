package com.klass.lab01.repository;

import com.klass.lab01.domain.Post;
import com.klass.lab01.dto.request.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {


    List<Post> findPostByAuthor(String filterName);

    @Query("UPDATE Post p set p =:post WHERE p.id = :id")
    int updatePost(long id,Post post);

//    Post update(long id, Post mappedPost);
}
