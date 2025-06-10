package com.archiadmin.service.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.service.dto.request.VASRequestDto;
import com.archiadmin.service.dto.response.VASResponseDto;
import com.archiadmin.service.service.VASService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vass")
@RequiredArgsConstructor
public class ServiceController {

    private final VASService vasService;

    @PostMapping
    public ResponseEntity<ApiResponse<VASResponseDto>> registerService(@RequestBody VASRequestDto vasRequestDto) {
        VASResponseDto data = vasService.registerService(vasRequestDto);
        return ResponseEntity.ok(ApiResponse.success("부가서비스 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<VASResponseDto>> getServiceList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        VASResponseDto data = vasService.getServiceList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 부가서비스 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<VASResponseDto>> getServiceById(@PathVariable Long serviceId) {
        VASResponseDto data = vasService.getServiceById(serviceId);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<VASResponseDto>> updateService(@PathVariable Long serviceId, @RequestBody VASRequestDto vasRequestDto) {
        VASResponseDto data = vasService.updateService(serviceId, vasRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<Void>> deleteService(@PathVariable Long serviceId) {
        vasService.deleteService(serviceId);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 삭제가 성공적으로 처리되었습니다.", null));
    }
}
