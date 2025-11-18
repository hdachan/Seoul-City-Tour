package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Tourist;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tourists")
public class TouristApiController {

    private final TouristRepository touristRepository;

    // DTO 추가: Guide 프록시 터지는 문제 완전 해결!
    record TouristDto(
            UUID id,
            String name,
            LocalDate birth,
            String phone,
            String country,
            String gender,
            OffsetDateTime time
    ) {
        // Tourist → TouristDto 변환
        static TouristDto from(Tourist t) {
            return new TouristDto(
                    t.getId(),
                    t.getName(),
                    t.getBirth(),
                    t.getPhone(),
                    t.getCountry(),
                    t.getGender(),
                    t.getTime()
            );
        }
    }

    @GetMapping
    public List<TouristDto> getTourists(@RequestParam UUID guideId,
                                        @RequestParam(required = false) String date) {
        List<Tourist> tourists;

        if (date != null && !date.isBlank()) {
            LocalDate localDate = LocalDate.parse(date);
            LocalDateTime start = localDate.atStartOfDay();
            LocalDateTime end = localDate.plusDays(1).atStartOfDay();
            tourists = touristRepository.findByGuideIdAndTimeBetweenOrderByTimeDesc(guideId, start, end);
        } else {
            tourists = touristRepository.findByGuideIdOrderByTimeDesc(guideId);
        }

        // 여기서 DTO로 변환해서 JSON 직렬화 문제 완전 해결!
        return tourists.stream()
                .map(TouristDto::from)
                .toList();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerTourist(@RequestBody Tourist tourist) {
        try {
            tourist.setTime(OffsetDateTime.now());
            touristRepository.save(tourist);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("등록 실패: " + e.getMessage());
        }
    }
}