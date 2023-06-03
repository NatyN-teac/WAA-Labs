package com.klass.lab01.dto.request;

import lombok.Data;

@Data
public class CommentDto {
    long id;
    long post_id;
    String name;

}
