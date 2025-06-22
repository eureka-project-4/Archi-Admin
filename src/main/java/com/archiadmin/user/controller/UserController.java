package com.archiadmin.user.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.common.response.CountResponseDto;
import com.archiadmin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<CountResponseDto>> countCoupon() {
        return ResponseEntity.ok(ApiResponse.success(userService.countUser()));
    }
}
