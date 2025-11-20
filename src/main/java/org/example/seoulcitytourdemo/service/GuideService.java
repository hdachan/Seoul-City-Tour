package org.example.seoulcitytourdemo.service;

import lombok.RequiredArgsConstructor;
import org.example.seoulcitytourdemo.dto.GuideDto;
import org.example.seoulcitytourdemo.dto.GuideRegisterRequest;
import org.example.seoulcitytourdemo.entity.Guide;

import org.example.seoulcitytourdemo.repository.GuideRepository;
import org.example.seoulcitytourdemo.repository.TouristRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // ===== 관리자 기능 =====

    public boolean existsByLoginId(String loginId) {
        return guideRepository.findByLoginId(loginId).isPresent();
    }

    public Guide register(GuideRegisterRequest req) {
        Guide guide = Guide.builder()
                .loginId(req.loginId())
                .password(req.password())
                .name(req.name())
                .birth(req.birth())
                .phone(req.phone())
                .nationality(req.nationality())
                .gender("남".equals(req.gender()) ? "MALE" : "FEMALE")
                .build();
        return guideRepository.save(guide);
    }

    @Transactional(readOnly = true)
    public List<GuideDto> getAllGuidesWithTouristCount() {
        List<Guide> guides = guideRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return guides.stream()
                .map(g -> GuideDto.from(g, touristRepository.countByGuideId(g.getId())))
                .toList();
    }

    @Transactional
    public void deleteGuide(UUID id) {
        touristRepository.deleteByGuideId(id);  // 연결된 관광객 모두 삭제
        guideRepository.deleteById(id);
    }
}