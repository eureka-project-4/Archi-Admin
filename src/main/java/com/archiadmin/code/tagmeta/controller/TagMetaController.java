package com.archiadmin.code.tagmeta.controller;

import com.archiadmin.code.tagmeta.dto.request.TagMetaDto;
import com.archiadmin.code.tagmeta.dto.response.TagMetaResponseDto;
import com.archiadmin.code.tagmeta.service.TagMetaService;
import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.common.response.CountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tagmeta")
@RequiredArgsConstructor
public class TagMetaController {
    private final TagMetaService tagMetaService;

    @PostMapping
    public ResponseEntity<ApiResponse<TagMetaResponseDto>> registerTagMeta(@RequestBody TagMetaDto tagMetaDto) {
        TagMetaResponseDto data = tagMetaService.registerTagMeta(tagMetaDto);
        return ResponseEntity.ok(ApiResponse.success("태그메타 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<TagMetaResponseDto>> getServiceList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        TagMetaResponseDto data = tagMetaService.getTagMetaList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 태그메타 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<TagMetaResponseDto>> getServiceById(@RequestParam String tagType, @RequestParam String tagKey) {
        TagMetaResponseDto data = tagMetaService.getTagMetaById(tagType, tagKey);
        return ResponseEntity.ok(ApiResponse.success("TagMeta Id [tagType : " + tagType + ", tagKey : " + tagKey + "] 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TagMetaResponseDto>> updateService(@RequestBody TagMetaDto tagMetaDto) {
        TagMetaResponseDto data = tagMetaService.updateTagMeta(tagMetaDto);
        return ResponseEntity.ok(ApiResponse.success("TagMeta Id [tagType : " + tagMetaDto.getTagType() + ", tagKey : " + tagMetaDto.getTagKey() + "] 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteService(@RequestParam String tagMeta, @RequestParam String tagKey) {
        tagMetaService.deleteTagMeta(tagMeta, tagKey);
        return ResponseEntity.ok(ApiResponse.success("TagMeta Id [tagType : " + tagMeta + ", tagKey : " + tagKey + "] 삭제가 성공적으로 처리되었습니다.", null));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<CountResponseDto>> countTagMeta() {
        return ResponseEntity.ok(ApiResponse.success(tagMetaService.countTagMeta()));
    }
}
