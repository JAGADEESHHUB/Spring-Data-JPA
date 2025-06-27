package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Lecture_Table")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture-seq-gen")
    @SequenceGenerator( name = "lecture-seq-gen", sequenceName = "L-seq-gen", allocationSize = 1 )
    private Integer id;

    private String name;

    //owner of Section
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    //inverse of Resource
    @OneToOne(mappedBy = "lecture")
    private Resources resources;
}
