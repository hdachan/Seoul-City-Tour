package org.example.seoulcitytourdemo.controller.web;

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

    // 관광객 폼은 URL의 guideId만 사용하므로 로그인 세션은 건드리지 않는다
    // (세션을 지우면 같은 브라우저에서 폼을 연 가이드/관리자가 로그아웃됨)
    @GetMapping("/register")
    public String registerForm(
            @RequestParam(required = false) UUID guideId,
            Model model) {

        if (guideId == null) {
            return "redirect:/";
        }

        Guide guide = guideService.findById(guideId);  // URL의 guideId로 강제 지정
        model.addAttribute("guide", guide);

        return "tourist/register";
    }
}