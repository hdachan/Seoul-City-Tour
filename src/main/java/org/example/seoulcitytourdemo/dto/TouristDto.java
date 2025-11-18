package org.example.seoulcitytourdemo.dto;

import org.example.seoulcitytourdemo.entity.Tourist;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TouristDto(
        UUID id, String name, LocalDate birth,
        String phone, String country, String gender,
        OffsetDateTime time
) {
    public static TouristDto from(Tourist t) {
        return new TouristDto(t.getId(), t.getName(), t.getBirth(),
                t.getPhone(), t.getCountry(), t.getGender(), t.getTime());
    }
}