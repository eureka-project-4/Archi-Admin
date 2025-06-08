package com.archiadmin.admin.service;

import com.archiadmin.admin.dto.request.AdminRegisterDto;
import com.archiadmin.admin.dto.response.AdminResultDto;

public interface AdminService {
    AdminResultDto register(AdminRegisterDto registerDto);
}
