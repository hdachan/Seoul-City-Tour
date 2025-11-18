package org.example.seoulcitytourdemo.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/guide")
public class GuideWebController {

    private final GuideService guideService;

    @PostMapping("/login")
    public String login(@RequestParam String loginId,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Guide guide = guideService.login(loginId, password);

        if (guide == null) {
            model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }

        session.setAttribute("loginGuide", guide);
        return "redirect:/guide/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Guide guide = (Guide) session.getAttribute("loginGuide");
        if (guide == null) {
            return "redirect:/";
        }

        model.addAttribute("guide", guide);

        // admin 계정은 관리자 페이지, 나머지는 일반 가이드 페이지
        if ("admin".equals(guide.getLoginId())) {
            return "admin/dashboard";   // templates/admin/dashboard.html
        } else {
            return "guide/dashboard";   // templates/guide/dashboard.html
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}