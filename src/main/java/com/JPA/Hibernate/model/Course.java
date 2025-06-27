package com.JPA.Hibernate.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Course_Table")
public class Course extends BaseEntities{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "course-seq-gen")
    @SequenceGenerator( name = "course-seq-gen", sequenceName = "C-seq-gen", allocationSize = 1 )
    private Integer id;

    private String title;

    private String description;

    //owner of Author //decided according to the logic flow
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "Author_course",
            joinColumns = {
                    @JoinColumn(name = "author_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            }
    )
    private List<Author> authors;

    //inverse of Section
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}

