package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.TouristDto;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tourists")
public class TouristApiController {

    private final TouristRepository touristRepository;

    @GetMapping
    public List<TouristDto> getTourists(
            @RequestParam UUID guideId,
            @RequestParam(required = false) String date) {

        if (date != null && !date.isBlank()) {
            LocalDate localDate = LocalDate.parse(date);
            OffsetDateTime start = localDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();
            OffsetDateTime end = localDate.plusDays(1).atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();

            return touristRepository.findByGuideIdAndTimeBetweenOrderByTimeDesc(guideId, start, end)
                    .stream()
                    .map(TouristDto::from)
                    .toList();
        } else {
            return touristRepository.findByGuideIdOrderByTimeDesc(guideId)
                    .stream()
                    .map(TouristDto::from)
                    .toList();
        }
    }
}