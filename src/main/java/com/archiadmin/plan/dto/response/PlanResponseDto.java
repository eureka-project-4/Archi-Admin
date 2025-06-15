package com.archiadmin.plan.dto.response;

import com.archiadmin.plan.dto.PlanDto;
import lombok.Data;

import java.util.List;

@Data
public class PlanResponseDto {
    private PlanDto planDto;
    private List<PlanDto> planDtoList;

}
