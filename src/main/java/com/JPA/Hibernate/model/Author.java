package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(
            name = "First_Name",
            length = 30 //this will make the length of the String char will be >= 30
    )
    private String firstName;

    private String lastName;

    @Column(
            unique = true , //  so that this field should be unique
            nullable = false // so that this field is mandatory
    )
    private String email;

    @Column(
            nullable = false,
            updatable = false, // not able to update once created
            insertable = false // During the creation of the object (PUT/POST/INSERT), it won’t work.
                               // But we keep that variable in Entity class because it's useful while getting data (GET/read).
            /*
             During insert (POST/PUT):
             You don’t need to set it, because the DB inserts it automatically.

             During read (GET):
             You do want it, so your app (e.g., frontend or API consumer) can see when the record was created.
             */
    )
    private LocalDateTime createdAt;

    @Column(
            nullable = false,
            insertable = false
    )
    private LocalDateTime updatedAt;

}
