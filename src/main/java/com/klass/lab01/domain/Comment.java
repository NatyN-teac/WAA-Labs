package com.klass.lab01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    long id;
    String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    Post post;
}
