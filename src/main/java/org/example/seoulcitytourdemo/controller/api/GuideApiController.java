package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.GuideDto;
import org.example.seoulcitytourdemo.dto.GuideRegisterRequest;  // ← 여기 추가!
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guides")
public class GuideApiController {

    private final GuideService guideService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Guide guide = guideService.login(req.loginId(), req.password());
        if (guide == null) {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
        return ResponseEntity.ok(guide);
    }

    // ===== 관리자 전용 API 시작 =====

    // 가이드 등록
    @PostMapping("/register")
    public ResponseEntity<?> registerGuide(@RequestBody GuideRegisterRequest req) {
        if (guideService.existsByLoginId(req.loginId())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 로그인 ID입니다.");
        }
        Guide guide = guideService.register(req);
        return ResponseEntity.ok(GuideDto.from(guide, 0L));
    }

    // 전체 가이드 목록 + 관광객 수
    @GetMapping
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        return ResponseEntity.ok(guideService.getAllGuidesWithTouristCount());
    }

    // 로그인 ID 중복 체크
    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkLoginId(@RequestParam String loginId) {
        return ResponseEntity.ok(guideService.existsByLoginId(loginId));
    }

    // 가이드 삭제 (연결된 관광객도 함께 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuide(@PathVariable UUID id) {
        guideService.deleteGuide(id);
        return ResponseEntity.ok().build();
    }

    // ===== DTO & Request =====
    record LoginRequest(String loginId, String password) {}

    // ← 여기 있던 GuideRegisterRequest record 완전히 삭제함!
}