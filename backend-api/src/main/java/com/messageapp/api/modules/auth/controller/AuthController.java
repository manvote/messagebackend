package com.messageapp.api.modules.auth.controller;

import com.messageapp.api.common.utils.PhoneUtils;
import com.messageapp.api.modules.auth.dto.LoginDto;
import com.messageapp.api.modules.auth.model.AuthResponse;
import com.messageapp.api.security.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OtpService otpService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginDto dto) {

        // phone validation (common utils)
        PhoneUtils.validateIndianPhone(dto.getPhone());

        otpService.sendOtp(dto.getPhone());

        return AuthResponse.success("OTP_SENT");
    }
}
