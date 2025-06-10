package com.archiadmin.code.groupcode.controller;

import com.archiadmin.code.groupcode.dto.request.GroupCodeDto;
import com.archiadmin.code.groupcode.dto.response.GroupCodeResponseDto;
import com.archiadmin.code.groupcode.service.GroupCodeService;
import com.archiadmin.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupcode")
@RequiredArgsConstructor
public class GroupCodeController {
    
    private final GroupCodeService groupCodeService;

    @PostMapping
    public ResponseEntity<ApiResponse<GroupCodeResponseDto>> registerGroupCode(@RequestBody GroupCodeDto groupCodeDto) {
        GroupCodeResponseDto data = groupCodeService.registerGroupCode(groupCodeDto);
        return ResponseEntity.ok(ApiResponse.success("그룹코드 등록이 성공적으로 처리되었습니다.", data));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<GroupCodeResponseDto>> getServiceList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        GroupCodeResponseDto data = groupCodeService.getGroupCodeList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 그룹코드 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<GroupCodeResponseDto>> getServiceById(@RequestParam String groupCode) {
        GroupCodeResponseDto data = groupCodeService.getGroupCodeById(groupCode);
        return ResponseEntity.ok(ApiResponse.success("GroupCode Id [groupCode : " + groupCode + ", groupCode : " + groupCode + "] 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<GroupCodeResponseDto>> updateService(@RequestBody GroupCodeDto groupCodeDto) {
        GroupCodeResponseDto data = groupCodeService.updateGroupCode(groupCodeDto);
        return ResponseEntity.ok(ApiResponse.success("GroupCode Id [groupCode : " + groupCodeDto.getGroupCode() + ", groupCode : " + groupCodeDto.getGroupCode() + "] 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteService(@RequestParam String groupCode) {
        groupCodeService.deleteGroupCode(groupCode);
        return ResponseEntity.ok(ApiResponse.success("GroupCode Id [groupCode : " + groupCode + ", groupCode : " + groupCode + "] 삭제가 성공적으로 처리되었습니다.", null));
    }
}