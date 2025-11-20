package org.example.seoulcitytourdemo.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

// 최종 수정본 (이걸로 통째로 교체!)
@Controller
@RequiredArgsConstructor
@RequestMapping("/tourist")
public class TouristWebController {

    private final GuideService guideService;  // 추가!

    @GetMapping("/register")
    public String registerForm(
            @RequestParam(required = false) UUID guideId,
            HttpSession session,
            Model model) {

        session.removeAttribute("loginGuide");  // 로그인 세션 강제 제거

        if (guideId == null) {
            return "redirect:/";
        }

        Guide guide = guideService.findById(guideId);  // URL의 guideId로 강제 지정
        model.addAttribute("guide", guide);

        return "tourist/register";
    }
}