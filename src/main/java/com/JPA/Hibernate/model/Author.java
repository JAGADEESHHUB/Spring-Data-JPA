package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Author_Table")
public class Author {
    @Id
    
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_gen") 
    @SequenceGenerator(name = "seq_gen", sequenceName = "author_seq_gen", allocationSize = 1) 
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

    @Column(
            nullable = false,
            updatable = false, 
            insertable = false 
                               
            
    )
    private LocalDateTime createdAt;

    @Column(
            nullable = false,
            insertable = false
    )
    private LocalDateTime updatedAt;

}
