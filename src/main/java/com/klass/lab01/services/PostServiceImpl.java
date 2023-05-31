package com.klass.lab01.services;

import com.klass.lab01.domain.Post;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;

    public List<PostDto> findPosts(){
        System.out.println("ONE = "+mapper.map(postRepository.findAll(),PostDto.class));
        return (List<PostDto>) postRepository.findAll()
                .stream()
                .map(m ->mapper.map(m,PostDto.class))
                .collect(Collectors.toList());

    }
    @Override
    public PostDto getPostById(long id) {
     return mapper.map(postRepository.findPostById(id),PostDto.class);
    }
    @Override
    public void save(PostDto post) {
        System.out.println("Posts are: => "+ post);
        postRepository.save(mapper.map(post, Post.class));
    }

    @Override
    public PostDto update(long id, PostDto postDto) {
        var mappedPost = mapper.map(postDto,Post.class);
        return mapper.map(postRepository.update(id,mappedPost),PostDto.class);
    }

    @Override
    public void delete(long id) {
        postRepository.delete(id);
    }

    @Override
    public List<PostDto> filterPostByAuthor(String filterName) {
        return postRepository.findPostByAuthor(filterName).stream().map(m -> mapper.map(m,PostDto.class)).collect(Collectors.toList());
    }
}
