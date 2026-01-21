package com.messageapp.api.modules.auth.controller;

import com.messageapp.api.modules.auth.dto.OtpDto;
import com.messageapp.api.modules.auth.model.AuthResponse;
import com.messageapp.api.modules.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OtpController {

    private final AuthService authService;

    @PostMapping("/verify")
    public AuthResponse verifyOtp(@RequestBody OtpDto dto) throws Throwable {
        return authService.verifyOtp(dto);
    }

    @PostMapping("/logout")
    public AuthResponse logout() {
        return AuthResponse.success("LOGOUT_SUCCESS");
    }
}
