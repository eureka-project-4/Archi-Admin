package com.archiadmin.common.config.security;

import com.archiadmin.admin.repository.AdminRepository;
import com.archiadmin.exception.business.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email)
                .map(AdminDetails::new)
                .orElseThrow(() -> new AdminNotFoundException(email + "not found"));
    }
}
