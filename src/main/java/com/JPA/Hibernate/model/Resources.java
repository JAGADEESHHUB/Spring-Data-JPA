package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Resources_Table")
public class Resources extends BaseEntities{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Resources-seq-gen")
    @SequenceGenerator( name = "Resources-seq-gen", sequenceName = "R-seq-gen", allocationSize = 1 )
    private Integer id;

    private String name;

    private Long size;

    private String url;

    //owner of Lecture
    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
