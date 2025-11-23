package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.TouristDto;
import org.example.seoulcitytourdemo.dto.TouristRegisterRequest;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.entity.Tourist;
import org.example.seoulcitytourdemo.repository.GuideRepository;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tourists")
public class TouristApiController {

    private final TouristRepository touristRepository;
    private final GuideRepository guideRepository;

    private static final ZoneId SEOUL = ZoneId.of("Asia/Seoul");

    // 가이드용 조회 – 하루 밀림 완전 해결!
    @GetMapping
    public List<TouristDto> getTourists(
            @RequestParam UUID guideId,
            @RequestParam(required = false) String date) {

        LocalDate targetDate = date != null && !date.isBlank()
                ? LocalDate.parse(date)
                : LocalDate.now(SEOUL);

        ZonedDateTime start = targetDate.atStartOfDay(SEOUL);
        ZonedDateTime end = targetDate.plusDays(1).atStartOfDay(SEOUL);

        return touristRepository
                .findByGuideIdAndTimeBetweenOrderByTimeDesc(
                        guideId,
                        start.toOffsetDateTime(),
                        end.toOffsetDateTime())
                .stream()
                .map(TouristDto::from)
                .toList();
    }

    // 관리자용 전체 조회
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
            LocalDate s = LocalDate.parse(start);
            LocalDate e = LocalDate.parse(end);
            result = touristRepository.findByTimeBetweenOrderByTimeDesc(
                            s.atStartOfDay(SEOUL).toOffsetDateTime(),
                            e.plusDays(1).atStartOfDay(SEOUL).toOffsetDateTime())
                    .stream()
                    .map(TouristDto::fromWithGuideInfo)
                    .toList();
        } else {
            result = touristRepository.findAllByOrderByTimeDesc()
                    .stream()
                    .map(TouristDto::fromWithGuideInfo)
                    .toList();
        }
        return ResponseEntity.ok(result);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable UUID id, HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body("권한 없음");
        }
        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // 등록 – 이제 @CreationTimestamp가 알아서 한국시간으로 넣어줌!
    @PostMapping("/register")
    public ResponseEntity<?> registerTourist(@RequestBody TouristRegisterRequest request) {
        if (request.guideId() == null || request.guideId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("가이드 정보가 없습니다.");
        }

        UUID guideId = UUID.fromString(request.guideId());
        Guide guide = guideRepository.findById(guideId)
                .orElse(null);
        if (guide == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 가이드입니다.");
        }

        Tourist tourist = Tourist.builder()
                .guide(guide)
                .name(request.name())
                .birth(request.birth())
                .phone(request.phone())
                .country(request.country())
                .gender(request.gender())
                // .time() 생략 → @CreationTimestamp가 자동으로 한국시간 넣음!
                .build();

        touristRepository.save(tourist);
        return ResponseEntity.ok().build();
    }
}