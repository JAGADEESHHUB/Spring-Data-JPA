package com.JPA.Hibernate.controller;

import com.JPA.Hibernate.model.Author;
import com.JPA.Hibernate.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Home")
public class Controller {

    private AuthorService authorService;

    public Controller(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/saveAuthor")
    public ResponseEntity<String> saveAuthor(@RequestBody Author author){
        return ResponseEntity.ok(authorService.SaveAuthor(author));
    }

}

