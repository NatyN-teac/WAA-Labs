package com.klass.lab01.controllers;

import com.klass.lab01.domain.User;
import com.klass.lab01.dto.UserDto;
import com.klass.lab01.dto.helpers.ListMapper;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.repository.UserRepository;
import com.klass.lab01.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@AllArgsConstructor
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
    public List<UserDto> getUsersWithMultiplePosts() {
        return userService.findUserWithMultiplePosts();
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
