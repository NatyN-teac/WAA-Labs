package com.klass.lab01.controllers;

import com.klass.lab01.aspect.annotations.ExecutionTime;
import com.klass.lab01.dto.request.CommentDto;
import com.klass.lab01.dto.request.SearchPostCriteriaRequest;
import com.klass.lab01.dto.request.UserDto;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@AllArgsConstructor
@Transactional
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final ModelMapper mapper;

    @GetMapping()
    public List<UserDto> getAllUsers(){
        return userService.fetchUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") long id){
        return userService.findUserById(id);
    }
    @GetMapping("/{id}/posts")
    public List<PostDto> getUserPosts(@PathVariable("id") long id) {
        return userService.findPostByUserId(id);
    }
    @GetMapping("/filtered")
    public List<UserDto> getUsersWithMultiplePosts(@RequestParam(name = "count") int count) {
        return userService.findUserWithMultiplePosts(count);
    }
    @GetMapping("/posts/search")
    public List<UserDto> getUsersByPostTitle(@RequestParam(name = "title") String title) {
        SearchPostCriteriaRequest request = new SearchPostCriteriaRequest(title);
        return userService.getUsersByPostTitle(request);
    }
    @GetMapping("/{id}/posts/{postId}/comments/{commentId}")
    @ExecutionTime()
    public CommentDto getCommentByUserPost(
            @PathVariable("id") long id,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {
        return userService.getUserComment(id,postId,commentId);

    }

    @GetMapping("/test")
    public void testException() {
        userService.throwException();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {

        userService.save(userDto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") long id,@RequestBody UserDto userDto) {
        userService.update(id,userDto);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }


}
