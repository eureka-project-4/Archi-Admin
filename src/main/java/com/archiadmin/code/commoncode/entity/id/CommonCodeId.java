package com.archiadmin.code.commoncode.entity.id;

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
public class CommonCodeId implements Serializable {

    @Column(name = "common_code", length = 3)
    private String commonCode;

    @Column(name = "group_code", length = 3)
    private String groupCode;
}
