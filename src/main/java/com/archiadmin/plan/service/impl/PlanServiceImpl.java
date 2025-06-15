package com.archiadmin.plan.service.impl;

import com.archiadmin.code.tagmeta.service.TagMetaService;
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
    private final TagMetaService tagMetaService;

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

        List<String> tagList = tagMetaService.extractTagsFromCode(plan.getTagCode());

        planResponseDto.setPlanDto(PlanDto.from(savedPlan, tagList));

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
            List<String> tagList = tagMetaService.extractTagsFromCode(plan.getTagCode());
            PlanDto planDto = PlanDto.from(plan, tagList);
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

        List<String> tagList = tagMetaService.extractTagsFromCode(plan.getTagCode());

        planResponseDto.setPlanDto(PlanDto.from(plan, tagList));

        return planResponseDto;
    }

    @Override
    public PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto) {
        PlanResponseDto planResponseDto = new PlanResponseDto();

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan Id " + planId + " Not Found"));

        plan.setPlanName(planRequestDto.getPlanName());
        plan.setMonthData(planRequestDto.getMonthData());
        plan.setCallUsage(planRequestDto.getCallUsage());
        plan.setMessageUsage(planRequestDto.getMessageUsage());
        plan.setBenefit(planRequestDto.getBenefit());
        plan.setTagCode(planRequestDto.getTagCode());
        plan.setAgeCode(planRequestDto.getAgeCode());
        plan.setCategoryCode(planRequestDto.getCategoryCode());

        Plan updatedPlan = planRepository.save(plan);

        List<String> tagList = tagMetaService.extractTagsFromCode(plan.getTagCode());

        planResponseDto.setPlanDto(PlanDto.from(updatedPlan, tagList));

        return planResponseDto;
    }

    @Override
    public void deletePlan(Long planId) {
        planRepository.findById(planId)
                .orElseThrow(() -> new DataNotFoundException("Plan Id " + planId + " Not Found"));

        planRepository.deleteById(planId);
    }
}
