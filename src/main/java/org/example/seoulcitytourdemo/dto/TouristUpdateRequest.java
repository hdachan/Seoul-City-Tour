// src/main/java/org/example/seoulcitytourdemo/dto/TouristUpdateRequest.java
package org.example.seoulcitytourdemo.dto;

import java.time.LocalDate;

public record TouristUpdateRequest(
        String name,
        LocalDate birth
) {}