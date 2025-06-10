package com.archiadmin.plan.service;

import com.archiadmin.plan.dto.request.PlanRequestDto;
import com.archiadmin.plan.dto.response.PlanResponseDto;

public interface PlanService {
    PlanResponseDto registerPlan(PlanRequestDto planRequestDto);

    PlanResponseDto getPlanList(Integer pageNumber, Integer pageSize);
    PlanResponseDto getPlanById(Long planId);

    PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto);

    void deletePlan(Long planId);

}
