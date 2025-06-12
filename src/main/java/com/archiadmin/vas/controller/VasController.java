package com.archiadmin.vas.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.vas.dto.request.VasRequestDto;
import com.archiadmin.vas.dto.response.VasResponseDto;
import com.archiadmin.vas.service.VasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vass")
@RequiredArgsConstructor
public class VasController {

    private final VasService vasService;

    @PostMapping
    public ResponseEntity<ApiResponse<VasResponseDto>> registerVas(@RequestBody VasRequestDto vasRequestDto) {
        VasResponseDto data = vasService.registerVas(vasRequestDto);
        return ResponseEntity.ok(ApiResponse.success("부가서비스 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<VasResponseDto>> getVasList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        VasResponseDto data = vasService.getVasList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 부가서비스 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/{vasId}")
    public ResponseEntity<ApiResponse<VasResponseDto>> getVasById(@PathVariable Long vasId) {
        VasResponseDto data = vasService.getVasById(vasId);
        return ResponseEntity.ok(ApiResponse.success("Vas Id " + vasId + " 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping("/{vasId}")
    public ResponseEntity<ApiResponse<VasResponseDto>> updateVas(@PathVariable Long vasId, @RequestBody VasRequestDto vasRequestDto) {
        VasResponseDto data = vasService.updateVas(vasId, vasRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Vas Id " + vasId + " 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping("/{vasId}")
    public ResponseEntity<ApiResponse<Void>> deleteVas(@PathVariable Long vasId) {
        vasService.deleteVas(vasId);
        return ResponseEntity.ok(ApiResponse.success("Vas Id " + vasId + " 삭제가 성공적으로 처리되었습니다.", null));
    }
}
