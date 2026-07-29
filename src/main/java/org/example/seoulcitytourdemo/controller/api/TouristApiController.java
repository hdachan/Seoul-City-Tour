// src/main/java/org/example/seoulcitytourdemo/controller/api/TouristApiController.java
package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.TouristDto;
import org.example.seoulcitytourdemo.dto.TouristRegisterRequest;
import org.example.seoulcitytourdemo.dto.TouristUpdateRequest;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.entity.Tourist;
import org.example.seoulcitytourdemo.repository.GuideRepository;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        LocalDateTime start = targetDate.atStartOfDay(SEOUL).toLocalDateTime();
        LocalDateTime end   = targetDate.plusDays(1).atStartOfDay(SEOUL).toLocalDateTime();

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

            LocalDateTime startTime = s.atStartOfDay(SEOUL).toLocalDateTime();
            LocalDateTime endTime   = e.plusDays(1).atStartOfDay(SEOUL).toLocalDateTime();

            result = touristRepository
                    .findByTimeBetweenOrderByTimeDesc(startTime, endTime)
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


    // ------------------------- [ 관리자 삭제 ] -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable UUID id, HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return ResponseEntity.status(403).body("권한 없음");
        }
        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    // ------------------------- [ 가이드 본인 삭제 ] -------------------------
    // guideId를 파라미터로 받아 ownership 체크 (세션 detach 문제 회피)
    @DeleteMapping("/my/{id}")
    public ResponseEntity<?> deleteMyTourist(
            @PathVariable UUID id,
            @RequestParam UUID guideId) {

        Tourist tourist = touristRepository.findById(id).orElse(null);
        if (tourist == null) {
            return ResponseEntity.notFound().build();
        }
        if (tourist.getGuide() == null ||
                !tourist.getGuide().getId().equals(guideId)) {
            return ResponseEntity.status(403).body("권한 없음");
        }

        touristRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    // ------------------------- [ 가이드 본인 수정 ] -------------------------
    @PutMapping("/my/{id}")
    public ResponseEntity<?> updateMyTourist(
            @PathVariable UUID id,
            @RequestParam UUID guideId,
            @RequestBody TouristUpdateRequest request) {

        Tourist tourist = touristRepository.findById(id).orElse(null);
        if (tourist == null) {
            return ResponseEntity.notFound().build();
        }
        if (tourist.getGuide() == null ||
                !tourist.getGuide().getId().equals(guideId)) {
            return ResponseEntity.status(403).body("권한 없음");
        }

        if (request.name() != null && !request.name().isBlank()) {
            tourist.setName(request.name());
        }
        if (request.birth() != null) {
            tourist.setBirth(request.birth());
        }

        touristRepository.save(tourist);
        return ResponseEntity.ok(TouristDto.from(tourist));
    }


    // ------------------------- [ 등록 ] -------------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerTourist(@RequestBody TouristRegisterRequest request) {

        if (request.guideId() == null || request.guideId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("가이드 정보가 없습니다.");
        }

        UUID guideUUID;
        try {
            guideUUID = UUID.fromString(request.guideId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("잘못된 가이드 ID 형식입니다.");
        }

        Guide guide = guideRepository.findById(guideUUID).orElse(null);
        if (guide == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 가이드입니다.");
        }

        LocalDateTime seoulNow = LocalDateTime.now(SEOUL);

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