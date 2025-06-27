package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Section_Table")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section-seq-gen")
    @SequenceGenerator( name = "section-seq-gen", sequenceName = "S-seq-gen", allocationSize = 1 )
    private Integer id;

    private String name;

    private Long order;

    //owner of Course // decided because 'Section dhaa Course oda id(as FK) vachu irukum'
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //inverse of Lecture
    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;
}
