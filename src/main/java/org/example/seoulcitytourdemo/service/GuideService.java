package org.example.seoulcitytourdemo.service;

import org.example.seoulcitytourdemo.entity.Guide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.seoulcitytourdemo.repository.GuideRepository;

@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;

    public Guide login(String loginId, String password) {
        return guideRepository.findByLoginId(loginId)
                .filter(g -> g.getPassword().equals(password))
                .orElse(null);
    }
}
