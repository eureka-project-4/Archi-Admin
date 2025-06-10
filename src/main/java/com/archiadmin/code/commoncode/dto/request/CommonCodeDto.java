package com.archiadmin.code.commoncode.dto.request;

import com.archiadmin.code.commoncode.entity.CommonCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonCodeDto {
    private String commonCode;
    private String groupCode;
    private String commonName;

    public static CommonCodeDto from(CommonCode commonCode) {
        return CommonCodeDto.builder()
                .commonCode(commonCode.getCommonCodeId().getCommonCode())
                .groupCode(commonCode.getCommonCodeId().getGroupCode())
                .commonName(commonCode.getCommonName())
                .build();
    }
}
