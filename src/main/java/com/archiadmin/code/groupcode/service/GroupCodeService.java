package com.archiadmin.code.groupcode.service;

import com.archiadmin.code.groupcode.dto.request.GroupCodeDto;
import com.archiadmin.code.groupcode.dto.response.GroupCodeResponseDto;

public interface GroupCodeService {
    GroupCodeResponseDto registerGroupCode(GroupCodeDto groupCodeDto);

    GroupCodeResponseDto getGroupCodeList(Integer pageNumber, Integer pageSize);
    GroupCodeResponseDto getGroupCodeById(String groupCode);

    GroupCodeResponseDto updateGroupCode(GroupCodeDto groupCodeDto);

    void deleteGroupCode(String groupCode);
}
