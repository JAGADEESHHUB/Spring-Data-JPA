package com.JPA.Hibernate.service;


import com.JPA.Hibernate.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo){
        this.authorRepo=authorRepo;
    }

    public String SaveAuthor(Author author){
        authorRepo.save(author);
        return "Saved successfully";
    }

}
