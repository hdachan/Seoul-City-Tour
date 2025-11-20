package org.example.seoulcitytourdemo.dto;

import org.example.seoulcitytourdemo.entity.Guide;

import java.time.LocalDate;
import java.util.UUID;

public record GuideDto(
        UUID id,
        String loginId,
        String password,
        String name,
        LocalDate birth,
        String phone,
        String nationality,
        String gender,
        long touristCount
) {
    public static GuideDto from(Guide guide, long touristCount) {
        return new GuideDto(
                guide.getId(),
                guide.getLoginId(),
                guide.getPassword(),
                guide.getName(),
                guide.getBirth(),
                guide.getPhone(),
                guide.getNationality(),
                guide.getGender(),
                touristCount
        );
    }
}