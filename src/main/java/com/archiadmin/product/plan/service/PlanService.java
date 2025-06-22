package com.archiadmin.product.plan.service;

import com.archiadmin.common.response.CountResponseDto;
import com.archiadmin.product.plan.dto.request.PlanRequestDto;
import com.archiadmin.product.plan.dto.response.PlanResponseDto;

public interface PlanService {
    PlanResponseDto registerPlan(PlanRequestDto planRequestDto);

    PlanResponseDto getPlanList(Integer pageNumber, Integer pageSize);
    PlanResponseDto getPlanById(Long planId);

    PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto);

    void deletePlan(Long planId);

    CountResponseDto countPlan();
}
