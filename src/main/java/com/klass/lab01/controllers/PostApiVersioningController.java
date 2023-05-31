package com.klass.lab01.controllers;

import com.klass.lab01.domain.PostV2;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostApiVersioningController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts/{id}",headers = "X-API-VERSION=1")
    public PostDto findPostById(@PathVariable("id") long id) {
        return postService.getPostById(id);
    }

    @GetMapping(value = "/posts/{id}",headers = "X-API-VERSION=2")
    public PostV2 postV2(@PathVariable("id") long id){
        var postV2 = new PostV2();
        postV2.setId(214);
        postV2.setTitle("Welcome Home");
        postV2.setContent("Introduction to our new Home");
        postV2.setAuthor("Gakneer");
        return postV2;
    }
}
