package com.JPA.Hibernate.repositories;

import com.JPA.Hibernate.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //Derived methods/Queries --> this is managed by Hibernate..

    List<Author> findByFirstNameContainingIgnoreCase(String firstName);

    List<Author> findByLastNameContainingIgnoreCase(String lastName);

    boolean existsByEmail(String email);
}