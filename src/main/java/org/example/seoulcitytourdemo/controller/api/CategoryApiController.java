// src/main/java/org/example/seoulcitytourdemo/controller/api/CategoryApiController.java
package org.example.seoulcitytourdemo.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.entity.Category;
import org.example.seoulcitytourdemo.repository.CategoryRepository;
import org.example.seoulcitytourdemo.service.GuideService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryRepository categoryRepository;
    private final GuideService guideService;

    // 응답용 DTO
    record CategoryResponse(
            Long id,
            String name,
            GuideInfo guide,
            String createdAt
    ) {}

    record GuideInfo(
            UUID id,
            String name,
            String loginId
    ) {}

    // 요청용 DTO
    record CreateRequest(String name, UUID guideId) {}

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        new GuideInfo(
                                category.getGuide().getId(),
                                category.getGuide().getName(),
                                category.getGuide().getLoginId()
                        ),
                        category.getCreatedAt() != null
                                ? category.getCreatedAt().toString()
                                : null
                ))
                .toList();
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CreateRequest req) {
        if (req.name() == null || req.name().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "카테고리 이름은 필수입니다.");
        }

        var guide = guideService.findById(req.guideId());  // 없으면 예외 터짐 (좋음!)

        var category = new Category();
        category.setName(req.name().trim());
        category.setGuide(guide);
        // createdAt은 엔티티에서 자동 설정

        category = categoryRepository.save(category);

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                new GuideInfo(guide.getId(), guide.getName(), guide.getLoginId()),
                category.getCreatedAt().toString()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다.");
        }
        categoryRepository.deleteById(id);
    }
}