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

    //owner of Author //decided according to the logic flow
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST /* PERSIST because I want to update according to the course and iam not using REMOVE and ALL(all contains REMOVE) coz both are independent */)
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

    //inverse of Section
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}

/*
✅cascade = ...
-1- Apply only on the owning side, because: Only owning side controls the DB operation.
-2- Cascade effects (PERSIST, REMOVE, etc.) are triggered from the owning side.

🔥 Why not on inverse side?
-1- Inverse side can't update the relationship.
-2- Setting cascade there has no real effect (won't propagate).

✅fetch = ...
-1- You can define fetch on either side, but it’s more useful on:
-2- The owning side (default fetch rules apply better).
-3- The side you're accessing the most in code.
*/

/*
✅ General Best Practices

Relationship	Recommended FetchType	Why
@ManyToOne	    EAGER (default)	        Usually small & needed immediately
@OneToOne	    EAGER (default)	        One-to-one → usually tightly bound
@OneToMany	    LAZY	                Might load huge collections
@ManyToMany 	LAZY	                Usually large and not always needed

 */