package com.archiadmin.user.service.impl;

import com.archiadmin.common.response.CountResponseDto;
import com.archiadmin.user.repository.UserRepository;
import com.archiadmin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public CountResponseDto countUser() {
        CountResponseDto countResponseDto = CountResponseDto.builder()
                .count(userRepository.count())
                .build();

        return countResponseDto;
    }
}
