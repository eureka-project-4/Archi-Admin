package com.archiadmin.code.tagmeta.dto.request;

import com.archiadmin.code.tagmeta.entity.TagMeta;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagMetaDto {
    private String tagType;
    private String tagKey;
    private String tagDescription;
    private Integer bitPosition;

    public static TagMetaDto from(TagMeta tagMeta) {
        return TagMetaDto.builder()
                .tagType(tagMeta.getId().getTagType())
                .tagKey(tagMeta.getId().getTagKey())
                .tagDescription(tagMeta.getTagDescription())
                .bitPosition(tagMeta.getBitPosition())
                .build();
    }
}
