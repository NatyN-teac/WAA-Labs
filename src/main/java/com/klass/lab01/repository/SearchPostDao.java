package com.klass.lab01.repository;

import com.klass.lab01.domain.Post;
import com.klass.lab01.domain.User;
import com.klass.lab01.dto.request.SearchPostCriteriaRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SearchPostDao {

    private final EntityManager entityManager;

    public List<Post> findAllUserWithTitle(SearchPostCriteriaRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> root = criteriaQuery.from(Post.class);
        Predicate titlePredicate = criteriaBuilder.equal(root.get("title"), request.getTitle());

        criteriaQuery.where(titlePredicate);
        TypedQuery<Post> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    public List<User> findUsersByPostTitle(SearchPostCriteriaRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User,Post> postJoin = root.join("posts");
        System.out.println("What is the title: " + request.getTitle());
        Predicate userPredicate = criteriaBuilder.equal(criteriaBuilder.lower(postJoin.get("title")),request.getTitle().toLowerCase());
//        Predicate finalPredicate = criteriaBuilder.and();
        criteriaQuery.where(userPredicate);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
