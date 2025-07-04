package com.archiadmin.common.config.security;

import com.archiadmin.common.config.security.handler.CustomAuthenticationFailureHandler;
import com.archiadmin.common.config.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    CustomAuthenticationSuccessHandler successHandler,
                                    CustomAuthenticationFailureHandler failureHandler) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/",
                                            "/csrf-token",
                                            "/admins/register",
                                    "/actuator/prometheus"
                                )
                                    .permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .formLogin(form ->
                        form
                                .loginPage("/index.html") // 로그인 페이지
                                .loginProcessingUrl("/admins/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                                .permitAll())
                .logout(logout -> logout.logoutUrl("/admins/logout").permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
