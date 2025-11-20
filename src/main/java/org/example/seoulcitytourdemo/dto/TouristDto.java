package org.example.seoulcitytourdemo.dto;

import org.example.seoulcitytourdemo.entity.Tourist;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TouristDto(
        UUID id,

        // 가이드 정보
        String guideName,
        String guideBirth,        // String으로 받음 (yyyy-MM-dd 형식)
        String guidePhone,
        String guideNationality,
        String guideGender,

        // 관광객 정보
        String name,
        String birth,             // 여기도 String으로 변경 (기존과 동일하게)
        String phone,
        String country,
        String gender,
        OffsetDateTime time
) {
    // 가이드용 (기존 그대로)
    public static TouristDto from(Tourist t) {
        return new TouristDto(
                t.getId(),
                t.getGuide().getName(),
                null, null, null, null,
                t.getName(),
                t.getBirth() != null ? t.getBirth().toString() : null,  // LocalDate → String
                t.getPhone(),
                t.getCountry(),
                t.getGender(),
                t.getTime()
        );
    }

    // 관리자용 전체 조회 → 가이드 상세 정보 포함
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
                t.getBirth() != null ? t.getBirth().toString() : null,  // 여기 고침!
                t.getPhone(),
                t.getCountry(),
                t.getGender(),
                t.getTime()
        );
    }
}