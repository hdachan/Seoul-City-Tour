package org.example.seoulcitytourdemo.entity;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CategoryGuideId implements Serializable {
    private UUID categoryId;
    private UUID guideId;
}