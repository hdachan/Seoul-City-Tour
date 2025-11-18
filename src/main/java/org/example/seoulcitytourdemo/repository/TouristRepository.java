package org.example.seoulcitytourdemo.repository;

import org.example.seoulcitytourdemo.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TouristRepository extends JpaRepository<Tourist, UUID> {

    List<Tourist> findByGuideIdOrderByTimeDesc(UUID guideId);

    @Query("SELECT t FROM Tourist t WHERE t.guide.id = :guideId AND t.time BETWEEN :start AND :end ORDER BY t.time DESC")
    List<Tourist> findByGuideIdAndTimeBetweenOrderByTimeDesc(
            @Param("guideId") UUID guideId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}