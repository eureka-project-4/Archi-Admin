package com.archiadmin.review.plan.entity;

import com.archiadmin.common.TimeStamp;
import com.archiadmin.product.plan.entity.Plan;
import com.archiadmin.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanReview extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_review_id")
    private Long planReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "content")
    private String content;

    @Column(name = "is_moderated", nullable = false)
    private Boolean isModerated = false;

    @Builder
    public PlanReview(User user, Plan plan, Integer score, String content) {
        this.user = user;
        this.plan = plan;
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
