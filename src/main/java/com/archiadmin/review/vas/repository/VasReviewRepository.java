package com.archiadmin.review.vas.repository;

import com.archiadmin.review.vas.entity.VasReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VasReviewRepository extends JpaRepository<VasReview, Long> {

    List<VasReview> findByIsModeratedFalse();

    @Query("SELECT v.vasId, COUNT(vr) FROM VasReview vr JOIN vr.vas v " +
            "WHERE vr.isModerated = true GROUP BY v.vasId HAVING COUNT(vr) >= 5")
    List<Object[]> findReviewGroupsByVas();

    @Query("SELECT vr.content FROM VasReview vr WHERE vr.vas.vasId = :vasId " +
            "AND vr.isModerated = true AND vr.score BETWEEN :minScore AND :maxScore")
    List<String> findContentsByVasIdAndScoreRange(@Param("vasId") Long vasId,
                                                  @Param("minScore") Integer minScore,
                                                  @Param("maxScore") Integer maxScore);

    @Query("SELECT vr.content FROM VasReview vr WHERE vr.vas.vasId = :vasId " +
            "AND vr.isModerated = true AND vr.score = :score")
    List<String> findContentsByVasIdAndScore(@Param("vasId") Long vasId,
                                             @Param("score") Integer score);
}

