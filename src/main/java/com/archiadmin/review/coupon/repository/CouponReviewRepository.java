package com.archiadmin.review.coupon.repository;

import com.archiadmin.review.coupon.entity.CouponReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponReviewRepository extends JpaRepository<CouponReview, Long> {
    List<CouponReview> findByIsModeratedFalse();

    @Query("SELECT c.couponId, COUNT(cr) FROM CouponReview cr JOIN cr.coupon c " +
            "WHERE cr.isModerated = true GROUP BY c.couponId HAVING COUNT(cr) >= 5")
    List<Object[]> findReviewGroupsByCoupon();

    @Query("SELECT cr.content FROM CouponReview cr WHERE cr.coupon.couponId = :couponId " +
            "AND cr.isModerated = true AND cr.score BETWEEN :minScore AND :maxScore")
    List<String> findContentsByCouponIdAndScoreRange(@Param("couponId") Long couponId,
                                                     @Param("minScore") Integer minScore,
                                                     @Param("maxScore") Integer maxScore);

    @Query("SELECT cr.content FROM CouponReview cr WHERE cr.coupon.couponId = :couponId " +
            "AND cr.isModerated = true AND cr.score = :score")
    List<String> findContentsByCouponIdAndScore(@Param("couponId") Long couponId,
                                                @Param("score") Integer score);
}
