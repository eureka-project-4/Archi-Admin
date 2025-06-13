package com.archiadmin.code.tagmeta.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TagMetaId implements Serializable {

    @Column(name = "tag_type", length = 20)
    private String tagType;

    @Column(name = "tag_key", length = 50)
    private String tagKey;
}

