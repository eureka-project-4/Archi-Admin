package com.archiadmin.code.commoncode.controller;

import com.archiadmin.code.commoncode.dto.request.CommonCodeDto;
import com.archiadmin.code.commoncode.dto.response.CommonCodeResponseDto;
import com.archiadmin.code.commoncode.service.CommonCodeService;
import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.common.response.CountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commoncode")
@RequiredArgsConstructor
public class CommonCodeController {
    
    private final CommonCodeService commonCodeService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommonCodeResponseDto>> registerCommonCode(@RequestBody CommonCodeDto commonCodeDto) {
        CommonCodeResponseDto data = commonCodeService.registerCommonCode(commonCodeDto);
        return ResponseEntity.ok(ApiResponse.success("공통코드 등록이 성공적으로 처리되었습니다.", data));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<CommonCodeResponseDto>> getServiceList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        CommonCodeResponseDto data = commonCodeService.getCommonCodeList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 공통코드 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<CommonCodeResponseDto>> getServiceById(@RequestParam String commonCode, @RequestParam String groupCode) {
        CommonCodeResponseDto data = commonCodeService.getCommonCodeById(commonCode, groupCode);
        return ResponseEntity.ok(ApiResponse.success("CommonCode Id [commonCode : " + commonCode + ", groupCode : " + groupCode + "] 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CommonCodeResponseDto>> updateService(@RequestBody CommonCodeDto commonCodeDto) {
        CommonCodeResponseDto data = commonCodeService.updateCommonCode(commonCodeDto);
        return ResponseEntity.ok(ApiResponse.success("CommonCode Id [commonCode : " + commonCodeDto.getCommonCode() + ", groupCode : " + commonCodeDto.getGroupCode() + "] 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteService(@RequestParam String commonCode, @RequestParam String groupCode) {
        commonCodeService.deleteCommonCode(commonCode, groupCode);
        return ResponseEntity.ok(ApiResponse.success("CommonCode Id [commonCode : " + commonCode + ", groupCode : " + groupCode + "] 삭제가 성공적으로 처리되었습니다.", null));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<CountResponseDto>> countCommonCode() {
        return ResponseEntity.ok(ApiResponse.success(commonCodeService.countCommonCode()));
    }
}