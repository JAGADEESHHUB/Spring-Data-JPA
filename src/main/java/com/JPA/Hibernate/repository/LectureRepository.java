package com.JPA.Hibernate.repository;

import com.JPA.Hibernate.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
}
