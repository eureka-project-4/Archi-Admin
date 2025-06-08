package com.archiadmin.admin.dto.response;

import com.archiadmin.admin.entity.Admin;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AdminRegisterResponseDto {

    private Long adminId;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AdminRegisterResponseDto from(Admin admin) {
        return AdminRegisterResponseDto.builder()
                .adminId(admin.getAdminId())
                .email(admin.getEmail())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}
