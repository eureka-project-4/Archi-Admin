package com.archiadmin.review.vas.entity;

import com.archiadmin.common.TimeStamp;
import com.archiadmin.product.vas.entity.Vas;
import com.archiadmin.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vas_reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VasReview extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vas_review_id")
    private Long vasReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vas_id", nullable = false)
    private Vas vas;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "content")
    private String content;

    @Column(name = "is_moderated", nullable = false)
    private Boolean isModerated = false;

    @Builder
    public VasReview(User user, Vas vas, Integer score, String content) {
        this.user = user;
        this.vas = vas;
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
