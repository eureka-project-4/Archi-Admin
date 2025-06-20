package com.archiadmin.scheduler.summary.dto;

import com.archiadmin.scheduler.summary.domain.ProductReviewSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedSummaryResult {
    private String highRatingSummary;
    private String lowRatingSummary;
    private Double averageScore;

    public static SimplifiedSummaryResult from(ProductReviewSummary productReviewSummary){
        return SimplifiedSummaryResult.builder()
                .highRatingSummary(productReviewSummary.getHighRatingSummary())
                .lowRatingSummary(productReviewSummary.getLowRatingSummary())
                .averageScore(productReviewSummary.getAverageScore())
                .build();
    }
}

