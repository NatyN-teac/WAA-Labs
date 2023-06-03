package com.klass.lab01.services;

import com.klass.lab01.domain.Comment;
import com.klass.lab01.dto.request.CommentDto;
import com.klass.lab01.dto.helpers.ListMapper;
import com.klass.lab01.repository.CommentRepository;
import com.klass.lab01.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private final ListMapper listMapper;

    public List<CommentDto> getAllComments(){
        return listMapper.mapList(commentRepository.findAll(),CommentDto.class);
    }
    public void saveComment(CommentDto commentDto) {
        var postInDbOpt = postRepository.findById(commentDto.getPost_id());
        if (postInDbOpt.isPresent()) {
            var post = postInDbOpt.get();
            var comment = mapper.map(commentDto, Comment.class);
            comment.setPost(post);
            post.setComment(comment);
            postRepository.save(post);
        }else {
            throw new RuntimeException();
        }
    }
}
