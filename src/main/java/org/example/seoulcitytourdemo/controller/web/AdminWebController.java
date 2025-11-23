package org.example.seoulcitytourdemo.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.repository.GuideRepository; // ← 추가!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminWebController {

    private final GuideRepository guideRepository; // ← 꼭 추가!

    // 관리자 대시보드
    @GetMapping("/admin")
    public String adminDashboard(HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");

        if (loginGuide == null) {
            return "redirect:/";
        }
        if (!"admin".equals(loginGuide.getLoginId())) {
            return "redirect:/guide/dashboard";
        }
        return "admin/dashboard";
    }

    // 가이드 관리 페이지 (iframe용)
    @GetMapping("/admin/register-guide.html")
    public String registerGuidePage(HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return "redirect:/";
        }
        return "admin/register-guide";
    }

    // 전체 관광객 명단 페이지 (iframe용)
    @GetMapping("/admin/all-tourists.html")
    public String allTouristsPage(HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return "redirect:/";
        }
        return "admin/allTourists";
    }

    // ★ 핵심: 특정 가이드의 관광객 전용 페이지 (이거 완전히 교체!)
    @GetMapping("/admin/guide-tourists.html")
    public String guideTouristsPage(
            @RequestParam UUID guideId,
            @RequestParam(required = false) String name, // ← required = false로 변경 (안전하게)
            HttpSession session,
            Model model) {

        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return "redirect:/";
        }

        // guideId로 실제 가이드 조회 (이름, 전화번호 가져오기 위해 필수!)
        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가이드입니다."));

        // 이름 디코딩 (URL에서 넘어온 한글 처리)
        String decodedName = name != null && !name.isBlank()
                ? URLDecoder.decode(name, StandardCharsets.UTF_8)
                : guide.getName();

        // 전화번호 처리 (없으면 기본값)
        String phone = guide.getPhone() != null && !guide.getPhone().trim().isEmpty()
                ? guide.getPhone()
                : "010-0000-0000";

        // 모델에 꼭 넣어줘야 HTML에서 th:text 작동함!
        model.addAttribute("guideId", guideId);
        model.addAttribute("guideName", decodedName);
        model.addAttribute("guidePhone", phone);
        model.addAttribute("name", URLEncoder.encode(decodedName, StandardCharsets.UTF_8)); // 제목용 인코딩

        return "admin/guide-tourists";  // → templates/admin/guide-tourists.html
    }
}