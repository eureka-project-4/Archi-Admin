package com.archiadmin.plan.dto;

import com.archiadmin.plan.entity.Plan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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
    private long tagCode;
    private String ageCode;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PlanDto from(Plan plan) {
        return PlanDto.builder()
                .planId(plan.getPlanId())
                .planName(plan.getPlanName())
                .monthData(plan.getMonthData())
                .callUsage(plan.getCallUsage())
                .messageUsage(plan.getMessageUsage())
                .benefit(plan.getBenefit())
                .tagCode(plan.getTagCode())
                .ageCode(plan.getAgeCode())
                .categoryCode(plan.getCategoryCode())
                .createdAt(plan.getCreatedAt())
                .updatedAt(plan.getUpdatedAt())
                .build();
    }
}
