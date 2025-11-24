package org.example.seoulcitytourdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "tourists")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "guide_id", columnDefinition = "binary(16)")
    private Guide guide;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    private String phone;

    @Column(name = "country", length = 100)
    private String country;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(name = "time", nullable = false, updatable = false,
            columnDefinition = "DATETIME(6)")
    private LocalDateTime time;
}