package com.archiadmin.code.commoncode.entity;

import com.archiadmin.code.commoncode.entity.id.CommonCodeId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "common_code")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonCode {

    @EmbeddedId
    private CommonCodeId commonCodeId;

    @Column(name = "common_name", length = 100)
    private String commonName;
}
