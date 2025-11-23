package org.example.seoulcitytourdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "tourists")
public class Tourist {

    @Id
    @GeneratedValue
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

    private String country;

    @Column(nullable = false, length = 10)
    private String gender; // MALE / FEMALE

    // 핵심! 이 두 어노테이션만 있으면 완벽!
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime time;
}