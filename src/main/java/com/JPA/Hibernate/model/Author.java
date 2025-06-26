package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
public class Author {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) // this will automatically choose the suitable type according to the RDBMS we are using
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // this will use the database’s built-in AUTO_INCREMENT.
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_gen") //
    @SequenceGenerator(name = "seq_gen", sequenceName = "author_seq_gen", allocationSize = 1) //Better performance than IDENTITY (because it pre-fetches IDs) but we cant use it on MySQL, we can only use this on postgresql and MSSQL
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

}
