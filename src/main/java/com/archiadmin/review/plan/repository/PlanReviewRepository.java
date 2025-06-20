package com.archiadmin.review.plan.repository;

import com.archiadmin.review.plan.entity.PlanReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanReviewRepository extends JpaRepository<PlanReview, Long> {

    List<PlanReview> findByIsModeratedFalse();

    @Query("SELECT p.planId, COUNT(pr) FROM PlanReview pr JOIN pr.plan p " +
            "WHERE pr.isModerated = true GROUP BY p.planId HAVING COUNT(pr) >= 5")
    List<Object[]> findReviewGroupsByPlan();

    @Query("SELECT pr.content FROM PlanReview pr WHERE pr.plan.planId = :planId " +
            "AND pr.isModerated = true AND pr.score BETWEEN :minScore AND :maxScore")
    List<String> findContentsByPlanIdAndScoreRange(@Param("planId") Long planId,
                                                   @Param("minScore") Integer minScore,
                                                   @Param("maxScore") Integer maxScore);

    @Query("SELECT pr.content FROM PlanReview pr WHERE pr.plan.planId = :planId " +
            "AND pr.isModerated = true AND pr.score = :score")
    List<String> findContentsByPlanIdAndScore(@Param("planId") Long planId,
                                              @Param("score") Integer score);
}
