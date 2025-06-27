package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true) /* We use it to tell Lombok: “Please include fields from the parent class when checking if two objects are equal or generating their hashcode.”*/
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Author_Table")
public class Author extends BaseEntities {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) // this will automatically choose the suitable type according to the RDBMS we are using
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // this will use the database’s built-in AUTO_INCREMENT.
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_gen") //
    @SequenceGenerator(name = "seq_gen", sequenceName = "author_seq_gen", allocationSize = 1) //Better performance than IDENTITY (because it pre-fetches IDs) but we cant use it on MySQL, we can only use this on postgresql and MSSQL
    private Integer id;

    @Column(
            name = "First_Name",
            length = 30
    )
    private String firstName;

    private String lastName;

    @Column(
            unique = true ,
            nullable = false
    )
    private String email;

    //inverse of Course
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}