package com.archiadmin.review.coupon.entity;

import com.archiadmin.common.TimeStamp;
import com.archiadmin.product.coupon.entity.Coupon;
import com.archiadmin.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponReview extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_review_id")
    private Long couponReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "content")
    private String content;

    @Column(name = "is_moderated", nullable = false)
    private Boolean isModerated = false;

    @Builder
    public CouponReview(User user, Coupon coupon, Integer score, String content) {
        this.user = user;
        this.coupon = coupon;
        this.score = score;
        this.content = content;
    }

    public void updateReview(Integer score, String content) {
        this.score = score;
        this.content = content;
        this.isModerated=false;
    }

    public void markAsModerated() {
        this.isModerated = true;
    }
}

