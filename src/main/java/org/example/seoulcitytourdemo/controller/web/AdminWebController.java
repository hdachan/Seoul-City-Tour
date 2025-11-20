package org.example.seoulcitytourdemo.controller.web;

import jakarta.servlet.http.HttpSession;
import org.example.seoulcitytourdemo.entity.Guide;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWebController {

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

    // 전체 관광객 명단 페이지 (iframe용) ← 새로 추가!
    @GetMapping("/admin/all-tourists.html")
    public String allTouristsPage(HttpSession session) {
        Guide loginGuide = (Guide) session.getAttribute("loginGuide");
        if (loginGuide == null || !"admin".equals(loginGuide.getLoginId())) {
            return "redirect:/";
        }
        return "admin/allTourists";  // → templates/admin/allTourists.html
    }
}