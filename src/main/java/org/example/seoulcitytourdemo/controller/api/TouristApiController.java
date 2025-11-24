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


    // ------------------------- [ 가이드별 조회 ] -------------------------
    @GetMapping
    public List<TouristDto> getTourists(
            @RequestParam UUID guideId,
            @RequestParam(required = false) String date) {

        LocalDate targetDate = (date != null && !date.isBlank())
                ? LocalDate.parse(date)
                : LocalDate.now(SEOUL);

        LocalDateTime start = targetDate.atStartOfDay();
        LocalDateTime end = targetDate.plusDays(1).atStartOfDay();

        return touristRepository
                .findByGuideIdAndTimeBetweenOrderByTimeDesc(guideId, start, end)
                .stream()
                .map(TouristDto::from)
                .toList();
    }


    // ------------------------- [ 관리자 전체 조회 ] -------------------------
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

            result = touristRepository
                    .findByTimeBetweenOrderByTimeDesc(
                            s.atStartOfDay(),
                            e.plusDays(1).atStartOfDay())
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


    // ------------------------- [ 삭제 ] -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable UUID id, HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body("권한 없음");
        }

        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    // ------------------------- [ 등록 ] -------------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerTourist(@RequestBody TouristRegisterRequest request) {

        if (request.guideId() == null || request.guideId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("가이드 정보가 없습니다.");
        }

        UUID guideId;
        try {
            guideId = UUID.fromString(request.guideId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("잘못된 가이드 ID 형식입니다.");
        }

        Guide guide = guideRepository.findById(guideId).orElse(null);
        if (guide == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 가이드입니다.");
        }

        // ✔ 한국 시간(LocalDateTime) 정확히 생성
        LocalDateTime seoulNow = LocalDateTime.now(SEOUL);

        System.out.println("▶ 한국시간 저장됨 = " + seoulNow);
        System.out.println("▶ JVM TimeZone = " + ZoneId.systemDefault());

        Tourist tourist = Tourist.builder()
                .guide(guide)
                .name(request.name())
                .birth(request.birth())
                .phone(request.phone())
                .country(request.country())
                .gender(request.gender())
                .time(seoulNow)
                .build();

        touristRepository.save(tourist);

        return ResponseEntity.ok().build();
    }
}
