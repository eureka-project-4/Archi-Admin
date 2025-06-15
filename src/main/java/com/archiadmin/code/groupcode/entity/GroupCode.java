package com.archiadmin.code.groupcode.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_code")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupCode {

    @Id
    @Column(name = "group_code", length = 3)
    private String groupCode;
    private String groupName;

}
