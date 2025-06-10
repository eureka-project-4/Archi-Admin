package com.archiadmin.code.commoncode.dto.response;

import com.archiadmin.code.commoncode.dto.request.CommonCodeDto;
import lombok.Data;

import java.util.List;

@Data
public class CommonCodeResponseDto {
    private CommonCodeDto commonCodeDto;
    private List<CommonCodeDto> commonCodeDtoList;
}
