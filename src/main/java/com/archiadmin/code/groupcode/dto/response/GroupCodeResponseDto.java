package com.archiadmin.code.groupcode.dto.response;

import com.archiadmin.code.groupcode.dto.request.GroupCodeDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupCodeResponseDto {
    private GroupCodeDto groupCodeDto;
    private List<GroupCodeDto> groupCodeDtoList;
}
