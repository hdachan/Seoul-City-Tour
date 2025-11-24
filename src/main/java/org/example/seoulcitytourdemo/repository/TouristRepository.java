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

    long countByGuideId(UUID guideId);

    void deleteByGuideId(UUID guideId);

    // ---------------- 관리자 전용 전체 조회 ----------------

    // 날짜 범위로 전체 관광객 조회 (가이드 상관없이)
    @Query("SELECT t FROM Tourist t WHERE t.time BETWEEN :start AND :end ORDER BY t.time DESC")
    List<Tourist> findByTimeBetweenOrderByTimeDesc(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // 날짜 필터 없이 전체 관광객 조회 (최신순)
    @Query("SELECT t FROM Tourist t ORDER BY t.time DESC")
    List<Tourist> findAllByOrderByTimeDesc();
}
