package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.TouristDto;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
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

    // 기존: 가이드별 관광객 조회 (가이드용)
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

    // 새로 추가: 관리자 전용 전체 관광객 조회 (날짜 필터 가능)
    @GetMapping("/all")
    public ResponseEntity<List<TouristDto>> getAllTourists(
            HttpSession session,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {

        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body(null);
        }

        List<TouristDto> result;

        if (start != null && end != null && !start.isBlank() && !end.isBlank()) {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            OffsetDateTime startTime = startDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();
            OffsetDateTime endTime = endDate.plusDays(1).atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();

            result = touristRepository.findByTimeBetweenOrderByTimeDesc(startTime, endTime)
                    .stream()
                    .map(TouristDto::fromWithGuideName)
                    .toList();
        } else {
            result = touristRepository.findAllByOrderByTimeDesc()
                    .stream()
                    .map(TouristDto::fromWithGuideName)
                    .toList();
        }

        return ResponseEntity.ok(result);
    }

    // 관광객 삭제 (관리자 전용)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable UUID id, HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body("권한 없음");
        }

        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}