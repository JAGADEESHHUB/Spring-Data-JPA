package com.JPA.Hibernate.service;

import com.JPA.Hibernate.model.Author;
import com.JPA.Hibernate.model.Course;
import com.JPA.Hibernate.repository.AuthorRepository;
import com.JPA.Hibernate.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl {

    private final AuthorRepository authorRepository;
    private final CourseRepository courseRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CourseRepository courseRepository) {
        this.authorRepository = authorRepository;
        this.courseRepository = courseRepository;
    }


    public Author createAuthor(Author author) {
        if (existsByEmail(author.getEmail())) {
            throw new RuntimeException("Author with email " + author.getEmail() + " already exists");
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setEmail(author.getEmail());

        return authorRepository.save(existingAuthor);
    }


    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id){
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Author with id is not Found"));
    }


    public void deleteAuthor(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return authorRepository.existsByEmail(email);
    }


    public Author assignCourseToAuthor(Integer authorId, Integer courseId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        if (!author.getCourses().contains(course)) {
            author.getCourses().add(course);
            course.getAuthors().add(author);
        }

        return authorRepository.save(author);
    }

    public Author removeCourseFromAuthor(Integer authorId, Integer courseId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        author.getCourses().remove(course);
        course.getAuthors().remove(author);

        return authorRepository.save(author);
    }
}