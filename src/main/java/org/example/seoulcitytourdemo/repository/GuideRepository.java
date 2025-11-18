package org.example.seoulcitytourdemo.repository;


import org.example.seoulcitytourdemo.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuideRepository extends JpaRepository<Guide, UUID> {
    Optional<Guide> findByLoginId(String loginId);
}
