package com.archiadmin.product.plan.dto;

import com.archiadmin.product.plan.entity.Plan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PlanDto {
    private Long planId;
    private String planName;
    private int price;
    private int monthData;
    private String callUsage;
    private String messageUsage;
    private String benefit;
    private List<String> tagList;
    private String ageCode;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PlanDto from(Plan plan, List<String> tagList) {
        return PlanDto.builder()
                .planId(plan.getPlanId())
                .planName(plan.getPlanName())
                .price(plan.getPrice())
                .monthData(plan.getMonthData())
                .callUsage(plan.getCallUsage())
                .messageUsage(plan.getMessageUsage())
                .benefit(plan.getBenefit())
                .tagList(tagList)
                .ageCode(plan.getAgeCode())
                .categoryCode(plan.getCategoryCode())
                .createdAt(plan.getCreatedAt())
                .updatedAt(plan.getUpdatedAt())
                .build();
    }
}
