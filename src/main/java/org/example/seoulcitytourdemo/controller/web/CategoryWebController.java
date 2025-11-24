// src/main/java/org/example/seoulcitytourdemo/controller/web/CategoryWebController.java
package org.example.seoulcitytourdemo.controller.web;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Category;
import org.example.seoulcitytourdemo.repository.CategoryRepository;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryWebController {

    private final CategoryRepository categoryRepository;
    private final GuideService guideService;

    // 카테고리별 인쇄용 명단 페이지
    @GetMapping("/{categoryId}")
    public String viewCategoryTourists(@PathVariable Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        model.addAttribute("guideId", category.getGuide().getId());
        model.addAttribute("guideName", category.getGuide().getName());
        model.addAttribute("guidePhone", category.getGuide().getPhone());
        model.addAttribute("categoryName", category.getName());

        return "admin/category-tourists";  // resources/templates/admin/category-tourists.html
    }
}