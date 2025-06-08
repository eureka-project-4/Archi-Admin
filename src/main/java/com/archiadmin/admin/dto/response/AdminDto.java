package com.archiadmin.admin.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
