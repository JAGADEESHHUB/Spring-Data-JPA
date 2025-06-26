package com.JPA.Hibernate.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Course_Table")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "course-seq-gen")
    @SequenceGenerator( name = "course-seq-gen", sequenceName = "C-seq-gen", allocationSize = 1 )
    private Integer id;

    private String title;

    private String description;
}
