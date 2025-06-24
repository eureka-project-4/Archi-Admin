package com.archiadmin.code.tagmeta.service;

import com.archiadmin.code.tagmeta.dto.request.TagMetaDto;
import com.archiadmin.code.tagmeta.dto.response.TagMetaResponseDto;
import com.archiadmin.common.response.CountResponseDto;

import java.util.List;

public interface TagMetaService {
    List<String> extractTagsFromCode(Long tagCode);

    TagMetaResponseDto registerTagMeta(TagMetaDto tagMetaDto);

    TagMetaResponseDto getTagMetaList(Integer pageNumber, Integer pageSize);
    TagMetaResponseDto getTagMetaById(String tagType, String tagKey);

    TagMetaResponseDto updateTagMeta(TagMetaDto tagMetaDto);

    void deleteTagMeta(String tagType, String tagKey);
    CountResponseDto countTagMeta();
}
