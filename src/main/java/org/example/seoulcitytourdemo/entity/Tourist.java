package org.example.seoulcitytourdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "tourists")
public class Tourist {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "guide_id", columnDefinition = "binary(16)")
    private Guide guide;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    private String phone;
    private String country;

    @Column(nullable = false)
    private String gender;

    private OffsetDateTime time = OffsetDateTime.now();
}