package com.klass.lab01.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    @Column(unique = true)
    String email;
    String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id"),

    }, inverseJoinColumns = {
            @JoinColumn(name = "roles_id", referencedColumnName = "id")
    })
    List<Role> roles = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    List<Post> posts = new ArrayList<>();

    public void addPost(Post posts) {
        this.posts.add(posts);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
