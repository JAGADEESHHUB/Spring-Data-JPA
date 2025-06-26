package com.JPA.Hibernate.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    //owner
    @ManyToMany
    @JoinTable(
            name = "Author_course",
            joinColumns = {
                    @JoinColumn(name = "author_id") // this joinColumns will responsible to store the owner's id
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id") // this inverseJoinColumns will responsible to store the inverse's id
            }
    )
    private List<Author> authors;

    //inverse
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}