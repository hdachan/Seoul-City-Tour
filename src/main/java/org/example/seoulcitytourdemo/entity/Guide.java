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
@Table(name = "guides")
public class Guide {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    private String phone;
    private String nationality;

    @Column(nullable = false)
    private String gender;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();
}