package org.example.seoulcitytourdemo.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Guide;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tourist")
public class TouristWebController {

    @GetMapping("/register")
    public String registerForm(HttpSession session, Model model) {
        Guide guide = (Guide) session.getAttribute("loginGuide");
        if (guide == null) {
            return "redirect:/";
        }
        model.addAttribute("guide", guide);
        return "tourist/register";
    }
}