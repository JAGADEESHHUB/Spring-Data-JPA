package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@MappedSuperclass
public class BaseEntities {

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
