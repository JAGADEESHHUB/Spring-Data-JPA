package com.JPA.Hibernate.repository;

import com.JPA.Hibernate.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //Derived method/Queries --> managed by Hibernate
    boolean existsByEmail(String email);
}