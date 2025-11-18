package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    record LoginRequest(String loginId, String password) {}
}