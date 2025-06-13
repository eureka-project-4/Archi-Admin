package com.archiadmin.code.tagmeta.dto.response;

import com.archiadmin.code.tagmeta.dto.request.TagMetaDto;
import lombok.Data;

import java.util.List;

@Data
public class TagMetaResponseDto {
    private TagMetaDto tagMetaDto;
    private List<TagMetaDto> tagMetaDtoList;
}
