package com.archiadmin.plan.dto.request;

import lombok.Getter;

@Getter
public class PlanRequestDto {
    private String planName;
    private int price;
    private int monthData;
    private String callUsage;
    private String messageUsage;
    private String benefit;
    private long tagCode;
    private String ageCode;
    private String categoryCode;
}
