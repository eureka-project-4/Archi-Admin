package com.archiadmin.admin.service.impl;

import com.archiadmin.admin.dto.request.AdminRegisterDto;
import com.archiadmin.admin.dto.response.AdminDto;
import com.archiadmin.admin.dto.response.AdminResultDto;
import com.archiadmin.admin.entity.Admin;
import com.archiadmin.admin.repository.AdminRepository;
import com.archiadmin.admin.service.AdminService;
import com.archiadmin.exception.BusinessException;
import com.archiadmin.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminResultDto register(AdminRegisterDto registerDto) {
        AdminResultDto adminResultDto = new AdminResultDto();

        if (adminRepository.existsByEmail(registerDto.getEmail())) {
            throw new BusinessException(ErrorCode.USER_ALREADY_EXISTS);
        }

        Admin admin = Admin.builder()
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        adminRepository.save(admin);

        return AdminResultDto.from(admin);
    }
}
