package com.archiadmin.admin.dto.response;

import com.archiadmin.admin.entity.Admin;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminDto {

    private Long adminId;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AdminDto from(Admin admin) {
        AdminDto dto = new AdminDto();
        dto.setAdminId(admin.getAdminId());
        dto.setEmail(admin.getEmail());
        dto.setCreatedAt(admin.getCreatedAt());
        dto.setUpdatedAt(admin.getUpdatedAt());
        return dto;
    }
}
