package org.example.seoulcitytourdemo.entity;


import lombok.Builder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category_guides")
@IdClass(CategoryGuideId.class)
public class CategoryGuide {

    @Id
    @Column(name = "category_id", columnDefinition = "binary(16)", nullable = false)
    private UUID categoryId;

    @Id
    @Column(name = "guide_id", columnDefinition = "binary(16)", nullable = false)
    private UUID guideId;
}
