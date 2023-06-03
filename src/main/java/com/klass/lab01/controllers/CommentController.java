package com.klass.lab01.controllers;

import com.klass.lab01.dto.request.CommentDto;
import com.klass.lab01.services.CommentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/comments")
@AllArgsConstructor
@Transactional
public class CommentController {
    @Autowired
    private final CommentService commentService;
    @GetMapping()
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments();
    }
    @PostMapping()
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.saveComment(commentDto);
    }
}
