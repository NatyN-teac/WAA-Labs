package com.klass.lab01.services;

import com.klass.lab01.domain.Post;
import com.klass.lab01.dto.helpers.ListMapper;
import com.klass.lab01.dto.request.PostDto;
import com.klass.lab01.dto.request.SearchPostCriteriaRequest;
import com.klass.lab01.repository.PostRepository;
import com.klass.lab01.repository.SearchPostDao;
import com.klass.lab01.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SearchPostDao searchPostDao;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;

    public List<PostDto> findPosts(){
        return (List<PostDto>) postRepository.findAll()
                .stream()
                .map(m ->mapper.map(m,PostDto.class))
                .collect(Collectors.toList());

    }
    @Override
    public PostDto getPostById(long id) {
        var postOpt = postRepository.findById(id);
        if(postOpt.isPresent()) {
        return mapper.map(postOpt.get(),PostDto.class);
        }
        throw new RuntimeException();
    }
    @Override
    public void save(PostDto postDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userInDb = userRepository.findByEmail(userDetails.getUsername());
        var post = mapper.map(postDto,Post.class);
        var user = userInDb.get();
        user.addPost(post);
        userRepository.save(user);

    }

    @Override
    public int update(long id, PostDto postDto) {
        var mappedPost = mapper.map(postDto,Post.class);
        return postRepository.updatePost(id,mappedPost);
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> filterPostByAuthor(String filterName) {
        return postRepository.findPostByAuthor(filterName).stream().map(m -> mapper.map(m,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostByTitle(String title) {
        SearchPostCriteriaRequest searchCriteria = new SearchPostCriteriaRequest(title);
        var result = searchPostDao.findAllUserWithTitle(searchCriteria);
        System.out.println("result is Here : " + result);
        return result.stream().map((m) -> mapper.map(m,PostDto.class)).collect(Collectors.toList());
    }
}
