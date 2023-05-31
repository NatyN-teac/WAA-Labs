package com.klass.lab01.dto.request;

import lombok.Data;

@Data
public class PostDto {
    long id;
    String title;
    String content;
    String author;


}
