package org.example.seoulcitytourdemo.repository;

import org.example.seoulcitytourdemo.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TouristRepository extends JpaRepository<Tourist, UUID> {

    List<Tourist> findByGuideIdOrderByTimeDesc(UUID guideId);

    @Query("SELECT t FROM Tourist t WHERE t.guide.id = :guideId AND t.time BETWEEN :start AND :end ORDER BY t.time DESC")
    List<Tourist> findByGuideIdAndTimeBetweenOrderByTimeDesc(
            @Param("guideId") UUID guideId,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end
    );

    // ===== 기존에 있던 메서드들 =====
    long countByGuideId(UUID guideId);

    void deleteByGuideId(UUID guideId);

    // ===== 관리자 전용 전체 조회용 (너 스타일에 딱 맞춰서 @Query로 추가!) =====

    // 1. 날짜 범위로 전체 관광객 조회 (가이드 상관없이)
    @Query("SELECT t FROM Tourist t WHERE t.time BETWEEN :start AND :end ORDER BY t.time DESC")
    List<Tourist> findByTimeBetweenOrderByTimeDesc(
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end
    );

    // 2. 날짜 필터 없이 전체 관광객 조회 (최신순)
    @Query("SELECT t FROM Tourist t ORDER BY t.time DESC")
    List<Tourist> findAllByOrderByTimeDesc();
}