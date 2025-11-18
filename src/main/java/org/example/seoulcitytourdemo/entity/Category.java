package org.example.seoulcitytourdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private OffsetDateTime createdAt = OffsetDateTime.now();
}