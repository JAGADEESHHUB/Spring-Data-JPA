package com.JPA.Hibernate.controller;

import com.JPA.Hibernate.model.Author;
import com.JPA.Hibernate.service.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    AuthorController(AuthorServiceImpl authorService){
        this.authorService =authorService;
    }


    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            Author createdAuthor = authorService.createAuthor(author);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, author);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{authorId}/courses/{courseId}")
    public ResponseEntity<Author> assignCourseToAuthor(@PathVariable Integer authorId, @PathVariable Integer courseId) {
        try {
            Author author = authorService.assignCourseToAuthor(authorId, courseId);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{authorId}/courses/{courseId}")
    public ResponseEntity<Author> removeCourseFromAuthor(@PathVariable Integer authorId, @PathVariable Integer courseId) {
        try {
            Author author = authorService.removeCourseFromAuthor(authorId, courseId);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

