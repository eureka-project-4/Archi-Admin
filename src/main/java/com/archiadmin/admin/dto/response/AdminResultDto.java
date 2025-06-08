package com.archiadmin.admin.dto.response;

import com.archiadmin.admin.entity.Admin;
import lombok.Data;

@Data
public class AdminResultDto {
    private AdminDto adminDto;

    public static AdminResultDto from(Admin admin) {
        AdminResultDto resultDto = new AdminResultDto();
        resultDto.setAdminDto(AdminDto.from(admin));
        return resultDto;
    }
}
