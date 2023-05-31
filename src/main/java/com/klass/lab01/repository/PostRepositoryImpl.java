package com.klass.lab01.repository;

import com.klass.lab01.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private static List<Post> posts;
    private static long postId = 202;
    static {
        posts = new ArrayList<>();
        var javaPost = new Post(150,"Introduction to Spring","This is content of introduction","James Roger");
        var pypost =  new Post(155,"Introduction to Python","zero to hero ","Graig Hamilton");
        posts.add(javaPost);
        posts.add(pypost);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post findPostById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @Override
    public void save(Post post) {
        post.setId(postId);
        postId++;
        posts.add(post);

    }

    @Override
    public Post update(long id, Post post) {
        var postToUpdate = getById(id);
        postToUpdate.setTitle(post.getTitle() != null ? post.getTitle() : postToUpdate.getTitle());
        postToUpdate.setContent(post.getContent()  != null ? post.getContent() : postToUpdate.getContent());
        postToUpdate.setAuthor(post.getAuthor() != null ? post.getAuthor() : postToUpdate.getAuthor());
        return postToUpdate;
    }

    @Override
    public void delete(long id) {
        var postToDelete = getById(id);
        posts.remove(postToDelete);

    }

    @Override
    public List<Post> findPostByAuthor(String filterName) {
        return posts.stream().filter(p -> p.getAuthor().equals(filterName)).collect(Collectors.toList());
    }

    private Post getById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
