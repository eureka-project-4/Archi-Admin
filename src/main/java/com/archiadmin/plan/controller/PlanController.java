package com.archiadmin.plan.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.plan.dto.request.PlanRequestDto;
import com.archiadmin.plan.dto.response.PlanResponseDto;
import com.archiadmin.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planservice;

    @PostMapping
    public ResponseEntity<ApiResponse<PlanResponseDto>> registerPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto data = planservice.registerPlan(planRequestDto);
        return ResponseEntity.ok(ApiResponse.success("요금제 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PlanResponseDto>> getPlanList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        PlanResponseDto data = planservice.getPlanList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 요금제 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<ApiResponse<PlanResponseDto>> getPlanById(@PathVariable Long planId) {
        PlanResponseDto data = planservice.getPlanById(planId);
        return ResponseEntity.ok(ApiResponse.success("Plan Id " + planId + " 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<ApiResponse<PlanResponseDto>> updatePlan(@PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto data = planservice.updatePlan(planId, planRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Plan Id " + planId + " 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<ApiResponse<Void>> deletePlan(@PathVariable Long planId) {
        planservice.deletePlan(planId);
        return ResponseEntity.ok(ApiResponse.success("Plan Id " + planId + " 삭제가 성공적으로 처리되었습니다.", null));
    }
}
