package com.klass.lab01.services;

import com.klass.lab01.dto.request.PostDto;

import java.util.List;


public interface PostService {

    public List<PostDto> findPosts();
    public PostDto getPostById(long id);
    public void save(PostDto post);
    int update(long id, PostDto postDto);

    void delete(long id);

    List<PostDto> filterPostByAuthor(String filterName);
}
