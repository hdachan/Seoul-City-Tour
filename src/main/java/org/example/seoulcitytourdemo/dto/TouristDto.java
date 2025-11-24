package org.example.seoulcitytourdemo.dto;

import org.example.seoulcitytourdemo.entity.Tourist;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import java.time.LocalDateTime;

public record TouristDto(
        UUID id,
        // 가이드 정보
        String guideName,
        String guideBirth,
        String guidePhone,
        String guideNationality,
        String guideGender,
        // 관광객 정보
        String name,
        String birth,
        String phone,
        String country,
        String gender,
        LocalDateTime time      // ← 여기 바꿔야 함
) {
    public static TouristDto from(Tourist t) {
        return new TouristDto(
                t.getId(),
                t.getGuide().getName(),
                null, null, null, null,
                t.getName(),
                t.getBirth() != null ? t.getBirth().toString() : null,
                t.getPhone(),
                t.getCountry(),
                t.getGender(),
                t.getTime()           // LocalDateTime 그대로 들어감
        );
    }

    public static TouristDto fromWithGuideInfo(Tourist t) {
        var guide = t.getGuide();
        return new TouristDto(
                t.getId(),
                guide.getName(),
                guide.getBirth() != null ? guide.getBirth().toString() : null,
                guide.getPhone(),
                guide.getNationality(),
                guide.getGender(),
                t.getName(),
                t.getBirth() != null ? t.getBirth().toString() : null,
                t.getPhone(),
                t.getCountry(),
                t.getGender(),
                t.getTime()           // LocalDateTime 그대로
        );
    }
}
