package com.JPA.Hibernate.service;

import com.JPA.Hibernate.model.Author;
import com.JPA.Hibernate.model.Course;
import com.JPA.Hibernate.repository.AuthorRepository;
import com.JPA.Hibernate.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl{

    private final CourseRepository courseRepository;
    private final AuthorRepository authorRepository;

    public CourseServiceImpl(CourseRepository courseRepository, AuthorRepository authorRepository) {
        this.courseRepository = courseRepository;
        this.authorRepository = authorRepository;
    }


    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());

        return courseRepository.save(existingCourse);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id){
        return courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course with is not found"));
    }

    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    public Course assignAuthorToCourse(Integer courseId, Integer authorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        if (!course.getAuthors().contains(author)) {
            course.getAuthors().add(author);
            author.getCourses().add(course);
        }

        return courseRepository.save(course);
    }

    public Course removeAuthorFromCourse(Integer courseId, Integer authorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        course.getAuthors().remove(author);
        author.getCourses().remove(course);

        return courseRepository.save(course);
    }
}
