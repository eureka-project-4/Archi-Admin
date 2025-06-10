package com.archiadmin.admin.controller;

import com.archiadmin.admin.dto.request.AdminRegisterRequestDto;
import com.archiadmin.admin.dto.response.AdminRegisterResponseDto;
import com.archiadmin.admin.service.AdminService;
import com.archiadmin.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins/")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AdminRegisterResponseDto>> register(@RequestBody AdminRegisterRequestDto registerDto) {
        AdminRegisterResponseDto data = adminService.register(registerDto);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공", data));
    }
}
