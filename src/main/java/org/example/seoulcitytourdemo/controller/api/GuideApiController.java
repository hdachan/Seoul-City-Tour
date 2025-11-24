// src/main/java/org/example/seoulcitytourdemo/controller/api/GuideApiController.java
package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.*;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GuideApiController {

    private final GuideService guideService;
    private final TouristRepository touristRepository;

    // 가이드 로그인
    @PostMapping("/guides/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Guide guide = guideService.login(req.loginId(), req.password());
        return guide != null
                ? ResponseEntity.ok(guide)
                : ResponseEntity.badRequest().body("로그인 실패");
    }

    // 가이드 등록
    @PostMapping("/guides/register")
    public ResponseEntity<?> registerGuide(@RequestBody GuideRegisterRequest req) {
        if (guideService.existsByLoginId(req.loginId())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 로그인 ID입니다.");
        }
        Guide guide = guideService.register(req);
        return ResponseEntity.ok(GuideDto.from(guide, 0L));
    }

    // 전체 가이드 목록 + 오늘 등록된 관광객 수
    @GetMapping("/guides")
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        return ResponseEntity.ok(guideService.getAllGuidesWithTouristCount());
    }

    // 로그인 ID 중복 체크
    @GetMapping("/guides/check-id")
    public ResponseEntity<Boolean> checkLoginId(@RequestParam String loginId) {
        return ResponseEntity.ok(guideService.existsByLoginId(loginId));
    }

    // 가이드 수정
    @PutMapping("/guides/{id}")
    public ResponseEntity<?> updateGuide(@PathVariable UUID id, @RequestBody GuideUpdateRequest req) {
        Guide updated = guideService.updateGuide(id, req);
        long count = touristRepository.countByGuideId(id);
        return ResponseEntity.ok(GuideDto.from(updated, count));
    }

    // 가이드 삭제
    @DeleteMapping("/guides/{id}")
    public ResponseEntity<?> deleteGuide(@PathVariable UUID id) {
        guideService.deleteGuide(id);
        return ResponseEntity.ok().build();
    }

    record LoginRequest(String loginId, String password) {}
}