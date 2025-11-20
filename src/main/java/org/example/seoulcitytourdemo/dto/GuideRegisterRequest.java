package org.example.seoulcitytourdemo.dto;

import java.time.LocalDate;

public record GuideRegisterRequest(
        String loginId,
        String password,
        String name,
        LocalDate birth,
        String phone,
        String nationality,
        String gender
) {}