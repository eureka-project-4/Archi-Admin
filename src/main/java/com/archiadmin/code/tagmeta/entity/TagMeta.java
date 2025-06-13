package com.archiadmin.code.tagmeta.entity;

import com.archiadmin.code.tagmeta.entity.id.TagMetaId;
import com.archiadmin.common.TimeStamp;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag_meta")
public class TagMeta extends TimeStamp {

    @EmbeddedId
    private TagMetaId id;

    @Column(name = "tag_description", length = 50)
    private String tagDescription;

    @Column(name = "bit_position")
    private Integer bitPosition;
}
