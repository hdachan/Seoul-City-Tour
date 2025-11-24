// src/main/java/org/example/seoulcitytourdemo/repository/TouristRepository.java
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

    // 오늘 등록된 관광객 수 (서울 시간 기준) - 완전히 안전한 방법
    default long countTodayByGuideIdInSeoul(UUID guideId) {
        var seoul = java.time.ZoneId.of("Asia/Seoul");
        var today = java.time.LocalDate.now(seoul);
        var start = today.atStartOfDay(seoul).toLocalDateTime();
        var end = today.plusDays(1).atStartOfDay(seoul).toLocalDateTime();
        return countByGuideIdAndTimeBetween(guideId, start, end);
    }

    // 범위 카운트 (재사용 가능)
    long countByGuideIdAndTimeBetween(UUID guideId, LocalDateTime start, LocalDateTime end);

    long countByGuideId(UUID guideId);
    void deleteByGuideId(UUID guideId);

    // 관리자 전체 조회
    @Query("SELECT t FROM Tourist t WHERE t.time BETWEEN :start AND :end ORDER BY t.time DESC")
    List<Tourist> findByTimeBetweenOrderByTimeDesc(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("SELECT t FROM Tourist t ORDER BY t.time DESC")
    List<Tourist> findAllByOrderByTimeDesc();
}