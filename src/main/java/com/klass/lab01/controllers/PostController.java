package com.klass.lab01.controllers;

import com.klass.lab01.domain.Post;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<PostDto> fetchPosts(){
        return postService.findPosts();
    }
    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable("id") long id){
        return postService.getPostById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> findPostByAuthor(@RequestParam(name = "filter") String filterName) {
        return postService.filterPostByAuthor(filterName);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto postDto) {
        postService.save(postDto);
    }

    @PutMapping(value = "/{id}")
    public PostDto update(@PathVariable("id") long id, @RequestBody PostDto postDto) {
        return postService.update(id,postDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable() long id) {
        postService.delete(id);
    }
}
