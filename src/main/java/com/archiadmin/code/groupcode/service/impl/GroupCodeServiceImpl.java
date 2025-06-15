package com.archiadmin.code.groupcode.service.impl;

import com.archiadmin.code.groupcode.dto.request.GroupCodeDto;
import com.archiadmin.code.groupcode.dto.response.GroupCodeResponseDto;
import com.archiadmin.code.groupcode.entity.GroupCode;
import com.archiadmin.code.groupcode.repository.GroupCodeRepository;
import com.archiadmin.code.groupcode.service.GroupCodeService;
import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.exception.business.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCodeServiceImpl implements GroupCodeService {

    private final GroupCodeRepository groupCodeRepository;

    @Override
    public GroupCodeResponseDto registerGroupCode(GroupCodeDto groupCodeDto) {
        GroupCodeResponseDto groupCodeResponseDto = new GroupCodeResponseDto();


        if (groupCodeRepository.existsById(groupCodeDto.getGroupCode())) {
            throw new DuplicateResourceException(("이미 존재하는 그룹코드 Id 입니다"));
        }

        GroupCode groupCode = GroupCode.builder()
                .groupCode(groupCodeDto.getGroupCode())
                .groupName(groupCodeDto.getGroupName())
                .build();

        GroupCode savedGroupCode = groupCodeRepository.save(groupCode);

        groupCodeResponseDto.setGroupCodeDto(GroupCodeDto.from(savedGroupCode));

        return groupCodeResponseDto;
    }

    @Override
    public GroupCodeResponseDto getGroupCodeList(Integer pageNumber, Integer pageSize) {
        GroupCodeResponseDto groupCodeResponseDto = new GroupCodeResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<GroupCode> groupCodePage = groupCodeRepository.findAll(pageable);
        List<GroupCode> groupCodeList = groupCodePage.toList();

        List<GroupCodeDto> groupCodeDtoList = new ArrayList<>();
        for (GroupCode groupCode : groupCodeList) {
            GroupCodeDto groupCodeDto = GroupCodeDto.from(groupCode);
            groupCodeDtoList.add(groupCodeDto);
        }

        groupCodeResponseDto.setGroupCodeDtoList(groupCodeDtoList);

        return groupCodeResponseDto;
    }

    @Override
    public GroupCodeResponseDto getGroupCodeById(String groupCode) {
        GroupCodeResponseDto groupCodeResponseDto = new GroupCodeResponseDto();

        GroupCode GroupCode = groupCodeRepository.findById(groupCode)
                .orElseThrow(() -> new DataNotFoundException("GroupCode Id " + groupCode + " Not Found"));

        groupCodeResponseDto.setGroupCodeDto(GroupCodeDto.from(GroupCode));

        return groupCodeResponseDto;
    }

    @Override
    public GroupCodeResponseDto updateGroupCode(GroupCodeDto groupCodeDto) {
        GroupCodeResponseDto groupCodeResponseDto = new GroupCodeResponseDto();


        GroupCode groupCode = groupCodeRepository.findById(groupCodeDto.getGroupCode())
                .orElseThrow(() -> new DataNotFoundException("GroupCode Id " + groupCodeDto.getGroupCode() + " Not Found"));

        groupCode.setGroupName(groupCodeDto.getGroupName());

        GroupCode savedGroupCode = groupCodeRepository.save(groupCode);

        groupCodeResponseDto.setGroupCodeDto(GroupCodeDto.from(savedGroupCode));

        return groupCodeResponseDto;
    }

    @Override
    public void deleteGroupCode(String groupCode) {
        groupCodeRepository.findById(groupCode)
                .orElseThrow(() -> new DataNotFoundException("GroupCode Id " + groupCode + " Not Found"));

        groupCodeRepository.deleteById(groupCode);
    }
}
