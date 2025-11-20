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
    private final GuideRepository guideRepository;

    // 가이드용: 자신의 관광객만 조회
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
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            OffsetDateTime startTime = startDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();
            OffsetDateTime endTime = endDate.plusDays(1).atStartOfDay(ZoneId.of("Asia/Seoul")).toOffsetDateTime();

            result = touristRepository.findByTimeBetweenOrderByTimeDesc(startTime, endTime)
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

    // 삭제 (관리자 전용)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable UUID id, HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body("권한 없음");
        }

        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // 완전 수정된 등록 API (이제 500 안 남!)
    @PostMapping("/register")
    public ResponseEntity<?> registerTourist(@RequestBody TouristRegisterRequest request) {

        // 1. guideId가 없거나 빈 문자열이면 400 반환
        if (request.guideId() == null || request.guideId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("가이드 정보가 없습니다.");
        }

        // 2. String → UUID 변환 (형식 오류 시 400)
        UUID guideId;
        try {
            guideId = UUID.fromString(request.guideId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("잘못된 가이드 ID 형식입니다.");
        }

        // 3. 실제 Guide 조회 (없으면 400)
        Guide guide = guideRepository.findById(guideId)
                .orElse(null);

        if (guide == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 가이드입니다.");
        }

        // 4. 정상 저장
        Tourist tourist = Tourist.builder()
                .guide(guide)
                .name(request.name())
                .birth(request.birth())
                .phone(request.phone())
                .country(request.country())
                .gender(request.gender())
                .time(OffsetDateTime.now())
                .build();

        touristRepository.save(tourist);
        return ResponseEntity.ok().build();
    }
}