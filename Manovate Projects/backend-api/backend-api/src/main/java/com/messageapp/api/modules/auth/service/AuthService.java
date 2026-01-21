package com.messageapp.api.modules.auth.service;

import com.messageapp.api.modules.auth.dto.OtpDto;
import com.messageapp.api.modules.auth.model.AuthResponse;

public interface AuthService {

    AuthResponse verifyOtp(OtpDto dto) throws Throwable;

}
