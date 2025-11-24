package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.GuideDto;
import org.example.seoulcitytourdemo.dto.GuideRegisterRequest;   // ← 이건 남겨둬!
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
        return guide != null
                ? ResponseEntity.ok(guide)
                : ResponseEntity.badRequest().body("로그인 실패");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerGuide(@RequestBody GuideRegisterRequest req) {
        if (guideService.existsByLoginId(req.loginId())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 로그인 ID입니다.");
        }
        Guide guide = guideService.register(req);
        return ResponseEntity.ok(GuideDto.from(guide, 0L));
    }

    @GetMapping
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        return ResponseEntity.ok(guideService.getAllGuidesWithTouristCount());
    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkLoginId(@RequestParam String loginId) {
        return ResponseEntity.ok(guideService.existsByLoginId(loginId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuide(@PathVariable UUID id) {
        guideService.deleteGuide(id);
        return ResponseEntity.ok().build();
    }

    // LoginRequest도 dto 패키지에 있으면 좋지만, 없으면 여기다 선언해도 됨
    // 근데 보통은 컨트롤러 전용 request는 여기 두는 게 맞아!
    record LoginRequest(String loginId, String password) {}
}