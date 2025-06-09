package com.archiadmin.plan.service.impl;

import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.plan.dto.PlanDto;
import com.archiadmin.plan.dto.request.PlanRequestDto;
import com.archiadmin.plan.dto.response.PlanResponseDto;
import com.archiadmin.plan.entity.Plan;
import com.archiadmin.plan.repository.PlanRepository;
import com.archiadmin.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public PlanResponseDto registerPlan(PlanRequestDto planRequestDto) {
        PlanResponseDto planResponseDto = new PlanResponseDto();

        Plan plan = Plan.builder()
                .planName(planRequestDto.getPlanName())
                .monthData(planRequestDto.getMonthData())
                .callUsage(planRequestDto.getCallUsage())
                .messageUsage(planRequestDto.getMessageUsage())
                .benefit(planRequestDto.getBenefit())
                .tagCode(planRequestDto.getTagCode())
                .ageCode(planRequestDto.getAgeCode())
                .categoryCode(planRequestDto.getCategoryCode())
                .build();

        Plan savedPlan = planRepository.save(plan);

        planResponseDto.setPlanDto(PlanDto.from(savedPlan));

        return planResponseDto;
    }

    @Override
    public PlanResponseDto getPlanList(Integer pageNumber, Integer pageSize) {
        PlanResponseDto planResponseDto = new PlanResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Plan> planPage = planRepository.findAll(pageable);
        List<Plan> planList = planPage.toList();

        List<PlanDto> planDtoList = new ArrayList<>();
        for (Plan plan : planList) {
            PlanDto planDto = PlanDto.from(plan);
            planDtoList.add(planDto);
        }

        planResponseDto.setPlanDtoList(planDtoList);

        return planResponseDto;
    }

    @Override
    public PlanResponseDto getPlanById(Long planId) {
        PlanResponseDto planResponseDto = new PlanResponseDto();

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan Id " + planId + " Not Found"));

        planResponseDto.setPlanDto(PlanDto.from(plan));

        return planResponseDto;
    }

    @Override
    public PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto) {
        PlanResponseDto planResponseDto = new PlanResponseDto();

        planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan Id " + planId + " Not Found"));

        Plan updatedPlan = Plan.builder()
                .planId(planId)
                .planName(planRequestDto.getPlanName())
                .monthData(planRequestDto.getMonthData())
                .callUsage(planRequestDto.getCallUsage())
                .messageUsage(planRequestDto.getMessageUsage())
                .benefit(planRequestDto.getBenefit())
                .tagCode(planRequestDto.getTagCode())
                .ageCode(planRequestDto.getAgeCode())
                .categoryCode(planRequestDto.getCategoryCode())
                .build();

        planRepository.save(updatedPlan);

        planResponseDto.setPlanDto(PlanDto.from(updatedPlan));

        return planResponseDto;
    }

    @Override
    public void deletePlan(Long planId) {
        planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan Id " + planId + " Not Found"));

        planRepository.deleteById(planId);
    }
}
