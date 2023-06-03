package com.klass.lab01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    String author;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    public void setComment(Comment comment) {
        comments.add(comment);
    }
}
