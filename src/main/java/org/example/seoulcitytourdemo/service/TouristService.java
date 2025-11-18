package org.example.seoulcitytourdemo.service;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Tourist;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TouristService {

    private final TouristRepository touristRepository;

    public List<Tourist> findByGuideId(UUID guideId) {
        return touristRepository.findByGuideIdOrderByTimeDesc(guideId);
    }

    public List<Tourist> findByGuideIdAndDate(UUID guideId, LocalDate date) {
        // Repository가 OffsetDateTime을 요구하니까 이렇게!
        OffsetDateTime start = date.atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();
        OffsetDateTime end = date.plusDays(1).atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();

        return touristRepository.findByGuideIdAndTimeBetweenOrderByTimeDesc(guideId, start, end);
    }
}