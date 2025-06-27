package com.JPA.Hibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@MappedSuperclass // it will not create any table in DB only used to provide things for base classes inherited by this SuperClass
public class BaseEntities {

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
