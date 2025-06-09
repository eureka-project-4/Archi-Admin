package com.archiadmin.service.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.service.dto.request.ServiceRequestDto;
import com.archiadmin.service.dto.response.ServiceResponseDto;
import com.archiadmin.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceResponseDto>> registerService(@RequestBody ServiceRequestDto serviceRequestDto) {
        ServiceResponseDto data = serviceService.registerService(serviceRequestDto);
        return ResponseEntity.ok(ApiResponse.success("부가서비스 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ServiceResponseDto>> getServiceList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        ServiceResponseDto data = serviceService.getServiceList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 부가서비스 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<ServiceResponseDto>> getServiceById(@PathVariable Long serviceId) {
        ServiceResponseDto data = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<ServiceResponseDto>> updateService(@PathVariable Long serviceId, @RequestBody ServiceRequestDto serviceRequestDto) {
        ServiceResponseDto data = serviceService.updateService(serviceId, serviceRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<Void>> deleteService(@PathVariable Long serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.ok(ApiResponse.success("Service Id " + serviceId + " 삭제가 성공적으로 처리되었습니다.", null));
    }
}
