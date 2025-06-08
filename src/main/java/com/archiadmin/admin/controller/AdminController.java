package com.archiadmin.admin.controller;

import com.archiadmin.admin.dto.request.AdminRegisterDto;
import com.archiadmin.admin.dto.response.AdminResultDto;
import com.archiadmin.admin.service.AdminService;
import com.archiadmin.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins/")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AdminResultDto>> register(@RequestBody AdminRegisterDto registerDto) {
        AdminResultDto data = adminService.register(registerDto);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공", data));
    }
}
