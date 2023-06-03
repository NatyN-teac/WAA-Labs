package com.klass.lab01.dto.request;

import com.klass.lab01.domain.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    long id;
    String name;
    List<Post> posts;
}
