package com.archiadmin.code.commoncode.service.impl;

import com.archiadmin.code.commoncode.dto.request.CommonCodeDto;
import com.archiadmin.code.commoncode.dto.response.CommonCodeResponseDto;
import com.archiadmin.code.commoncode.entity.CommonCode;
import com.archiadmin.code.commoncode.entity.id.CommonCodeId;
import com.archiadmin.code.commoncode.repository.CommonCodeRepository;
import com.archiadmin.code.commoncode.service.CommonCodeService;
import com.archiadmin.common.response.CountResponseDto;
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
public class CommonCodeServiceImpl implements CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    @Override
    public CommonCodeResponseDto registerCommonCode(CommonCodeDto commonCodeDto) {
        CommonCodeResponseDto commonCodeResponseDto = new CommonCodeResponseDto();

        CommonCodeId commonCodeId = new CommonCodeId(commonCodeDto.getCommonCode(), commonCodeDto.getGroupCode());

        if (commonCodeRepository.existsById(commonCodeId)) {
            throw new DuplicateResourceException(("이미 존재하는 공통코드 Id 입니다"));
        }

        CommonCode commonCode = CommonCode.builder()
                .commonCodeId(commonCodeId)
                .commonName(commonCodeDto.getCommonName())
                .build();

        CommonCode savedCommonCode = commonCodeRepository.save(commonCode);

        commonCodeResponseDto.setCommonCodeDto(CommonCodeDto.from(savedCommonCode));

        return commonCodeResponseDto;
    }

    @Override
    public CommonCodeResponseDto getCommonCodeList(Integer pageNumber, Integer pageSize) {
        CommonCodeResponseDto commonCodeResponseDto = new CommonCodeResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CommonCode> commonCodePage = commonCodeRepository.findAll(pageable);
        List<CommonCode> commonCodeList = commonCodePage.toList();

        List<CommonCodeDto> commonCodeDtoList = new ArrayList<>();
        for (CommonCode commonCode : commonCodeList) {
            CommonCodeDto commonCodeDto = CommonCodeDto.from(commonCode);
            commonCodeDtoList.add(commonCodeDto);
        }

        commonCodeResponseDto.setCommonCodeDtoList(commonCodeDtoList);

        return commonCodeResponseDto;
    }

    @Override
    public CommonCodeResponseDto getCommonCodeById(String commonCode, String groupCode) {
        CommonCodeResponseDto commonCodeResponseDto = new CommonCodeResponseDto();

        CommonCodeId commonCodeId = new CommonCodeId(commonCode, groupCode);

        CommonCode CommonCode = commonCodeRepository.findById(commonCodeId)
                .orElseThrow(() -> new DataNotFoundException("CommonCode Id " + commonCodeId + " Not Found"));

        commonCodeResponseDto.setCommonCodeDto(CommonCodeDto.from(CommonCode));

        return commonCodeResponseDto;
    }

    @Override
    public CommonCodeResponseDto updateCommonCode(CommonCodeDto commonCodeDto) {
        CommonCodeResponseDto commonCodeResponseDto = new CommonCodeResponseDto();

        CommonCodeId commonCodeId = new CommonCodeId(commonCodeDto.getCommonCode(), commonCodeDto.getGroupCode());

        CommonCode commonCode = commonCodeRepository.findById(commonCodeId)
                .orElseThrow(() -> new DataNotFoundException("CommonCode Id " + commonCodeId + " Not Found"));

        commonCode.setCommonName(commonCodeDto.getCommonName());

        CommonCode savedCommonCode = commonCodeRepository.save(commonCode);

        commonCodeResponseDto.setCommonCodeDto(CommonCodeDto.from(savedCommonCode));

        return commonCodeResponseDto;
    }

    @Override
    public void deleteCommonCode(String commonCode, String groupCode) {
        CommonCodeId commonCodeId = new CommonCodeId(commonCode, groupCode);

        commonCodeRepository.findById(commonCodeId)
                .orElseThrow(() -> new DataNotFoundException("CommonCode Id " + commonCodeId + " Not Found"));

        commonCodeRepository.deleteById(commonCodeId);
    }

    @Override
    public CountResponseDto countCommonCode() {
        CountResponseDto countResponseDto =  CountResponseDto.builder()
                .count(commonCodeRepository.count())
                .build();

        return countResponseDto;
    }
}
