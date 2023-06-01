package com.klass.lab01.services;

import com.klass.lab01.domain.User;
import com.klass.lab01.dto.UserDto;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;


    public List<UserDto> fetchUsers(){
        return userRepository.findAll().stream().map((m) -> mapper.map(m,UserDto.class)).collect(Collectors.toList());
    }

    public List<PostDto> findPostByUserId(long id) {
        return userRepository.findUserPostsById(id).stream().map((m) -> mapper.map(m,PostDto.class)).collect(Collectors.toList());
    }
    public  List<UserDto> findUserWithMultiplePosts() {
        return userRepository.findUsersByPostsCount().stream().map((m) -> mapper.map(m, UserDto.class)).collect(Collectors.toList());
    }
    public UserDto findUserById(long id) {
      var userOpt = userRepository.findById(id);
      if(userOpt.isPresent()) {
          return mapper.map(userOpt.get(),UserDto.class);
      }
      throw new RuntimeException();

    }

    public void save(UserDto userDto) {
        userRepository.save(mapper.map(userDto, User.class));
    }
    public void delete(long id) {
        userRepository.deleteById(id);
    }
    public void update(long id, UserDto userDto) {
        var userOptional = userRepository.findById(id);
        var mappedUser = mapper.map(userDto,User.class);
        if(userOptional.isPresent()) {
            var user = userOptional.get();
              user.setName(mappedUser.getName());
              user.setPosts(mappedUser.getPosts());

              userRepository.save(user);
        }
     }
}
