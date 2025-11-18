package org.example.seoulcitytourdemo.service;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Tourist;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return touristRepository.findByGuideIdAndTimeBetweenOrderByTimeDesc(guideId, start, end);
    }
}