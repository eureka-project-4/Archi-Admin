package com.archiadmin.code.commoncode.service;

import com.archiadmin.code.commoncode.dto.request.CommonCodeDto;
import com.archiadmin.code.commoncode.dto.response.CommonCodeResponseDto;
import com.archiadmin.common.response.CountResponseDto;

public interface CommonCodeService {
    CommonCodeResponseDto registerCommonCode(CommonCodeDto commonCodeDto);

    CommonCodeResponseDto getCommonCodeList(Integer pageNumber, Integer pageSize);
    CommonCodeResponseDto getCommonCodeById(String commonCode, String groupCode);

    CommonCodeResponseDto updateCommonCode(CommonCodeDto commonCodeDto);

    void deleteCommonCode(String commonCode, String groupCode);

    CountResponseDto countCommonCode();
}
