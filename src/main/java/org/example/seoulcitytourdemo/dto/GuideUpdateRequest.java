package org.example.seoulcitytourdemo.dto;

import java.time.LocalDate;

public record GuideUpdateRequest(
        String loginId,
        String password,        // 빈 문자열 또는 null → 비밀번호 변경 안 함
        String name,
        LocalDate birth,
        String phone,
        String nationality,
        String gender           // "남" 또는 "여"
) {}