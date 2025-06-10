package com.archiadmin.code.groupcode.dto.request;

import com.archiadmin.code.groupcode.entity.GroupCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupCodeDto {
    private String groupCode;
    private String groupName;

    public static GroupCodeDto from(GroupCode groupCode) {
        return GroupCodeDto.builder()
                .groupCode(groupCode.getGroupCode())
                .groupName(groupCode.getGroupName())
                .build();
    }
}
