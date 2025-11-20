package org.example.seoulcitytourdemo.dto;

import org.example.seoulcitytourdemo.entity.Tourist;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TouristDto(
        UUID id,
        String guideName,        // 관리자용 추가 필드
        String name,
        LocalDate birth,
        String phone,
        String country,
        String gender,           // ← "남"/"여" 또는 "MALE"/"FEMALE" 전달
        OffsetDateTime time
) {
    // 기존 가이드용 API (기존 동작 그대로 유지)
    public static TouristDto from(Tourist t) {
        return new TouristDto(
                t.getId(),
                null,  // guideName은 null → 기존 가이드 앱에 영향 없음
                t.getName(),
                t.getBirth(),
                t.getPhone(),
                t.getCountry(),
                t.getGender(),  // 그대로 "MALE" 또는 "FEMALE" 전달 → 기존 가이드 앱 완벽 동작
                t.getTime()
        );
    }

    // 관리자 전용: 가이드 이름 + 성별을 "남"/"여"로 변환해서 보여줌
    public static TouristDto fromWithGuideName(Tourist t) {
        String guideName = t.getGuide() != null ? t.getGuide().getName() : "알수없음";

        String displayGender = null;
        if (t.getGender() != null) {
            displayGender = "MALE".equalsIgnoreCase(t.getGender()) ? "남" : "여";
        }

        return new TouristDto(
                t.getId(),
                guideName,
                t.getName(),
                t.getBirth(),
                t.getPhone(),
                t.getCountry(),
                displayGender,  // 관리자 화면에서는 "남"/"여"로 보여줌
                t.getTime()
        );
    }
}