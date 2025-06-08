package com.archiadmin.admin.service;

import com.archiadmin.admin.dto.request.AdminRegisterRequestDto;
import com.archiadmin.admin.dto.response.AdminRegisterResponseDto;

public interface AdminService {
    AdminRegisterResponseDto register(AdminRegisterRequestDto registerDto);
}
