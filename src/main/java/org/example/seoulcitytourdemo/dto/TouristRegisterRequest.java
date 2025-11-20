// src/main/java/org/example/seoulcitytourdemo/dto/TouristRegisterRequest.java
package org.example.seoulcitytourdemo.dto;

import java.time.LocalDate;

public record TouristRegisterRequest(
        String guideId,      // ← 여기만 String으로 변경!
        String name,
        LocalDate birth,
        String phone,
        String country,
        String gender
) {}