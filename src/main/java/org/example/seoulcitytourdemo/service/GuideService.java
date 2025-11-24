// src/main/java/org/example/seoulcitytourdemo/service/GuideService.java
package org.example.seoulcitytourdemo.service;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.GuideDto;
import org.example.seoulcitytourdemo.dto.GuideRegisterRequest;
import org.example.seoulcitytourdemo.dto.GuideUpdateRequest;
import org.example.seoulcitytourdemo.entity.Guide;
import org.example.seoulcitytourdemo.repository.GuideRepository;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;

    public Guide login(String loginId, String password) {
        return guideRepository.findByLoginId(loginId)
                .filter(g -> g.getPassword().equals(password))
                .orElse(null);
    }

    public boolean existsByLoginId(String loginId) {
        return guideRepository.findByLoginId(loginId).isPresent();
    }

    @Transactional
    public Guide register(GuideRegisterRequest req) {
        Guide guide = Guide.builder()
                .loginId(req.loginId())
                .password(req.password())
                .name(req.name())
                .birth(req.birth())
                .phone(req.phone())
                .nationality(req.nationality())
                .gender("남".equals(req.gender()) ? "MALE" : "FEMALE")
                .createdAt(LocalDateTime.now())
                .build();
        return guideRepository.save(guide);
    }

    // 오늘 등록된 관광객 수 포함한 가이드 목록
    @Transactional(readOnly = true)
    public List<GuideDto> getAllGuidesWithTouristCount() {
        List<Guide> guides = guideRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return guides.stream()
                .map(g -> {
                    long todayCount = touristRepository.countTodayByGuideIdInSeoul(g.getId());
                    return GuideDto.from(g, todayCount);
                })
                .toList();
    }

    @Transactional
    public void deleteGuide(UUID id) {
        touristRepository.deleteByGuideId(id);
        guideRepository.deleteById(id);
    }

    @Transactional
    public Guide updateGuide(UUID id, GuideUpdateRequest req) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가이드를 찾을 수 없습니다: " + id));

        if (!guide.getLoginId().equals(req.loginId())) {
            if (existsByLoginId(req.loginId())) {
                throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
            }
            guide.setLoginId(req.loginId());
        }

        if (req.password() != null && !req.password().trim().isEmpty()) {
            guide.setPassword(req.password().trim());
        }

        guide.setName(req.name());
        guide.setBirth(req.birth());
        guide.setPhone(req.phone());
        guide.setNationality(req.nationality());
        guide.setGender("남".equals(req.gender()) ? "MALE" : "FEMALE");

        return guideRepository.save(guide);
    }

    public Guide findById(UUID id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 가이드를 찾을 수 없습니다: " + id));
    }
}