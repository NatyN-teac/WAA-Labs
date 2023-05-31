package com.klass.lab01.repository;

import com.klass.lab01.domain.Post;
import com.klass.lab01.dto.request.PostDto;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    Post findPostById(long id);
    void save(Post post);
    Post update(long id, Post mappedPost);

    void delete(long id);

    List<Post> findPostByAuthor(String filterName);
}
