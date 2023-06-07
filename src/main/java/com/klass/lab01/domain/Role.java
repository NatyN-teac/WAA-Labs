package com.klass.lab01.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Roles")
@Setter
@Getter
@NoArgsConstructor
public class Role {
   public  Role(String name){
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    List<User> user = new ArrayList<>();
}
