package com.messageapp.api.modules.auth.service;

import com.messageapp.api.common.exceptions.ApiException;
import com.messageapp.api.modules.auth.dto.OtpDto;
import com.messageapp.api.modules.auth.model.AuthResponse;
import com.messageapp.api.modules.user.model.user;
import com.messageapp.api.modules.user.repository.RepositoryClass;
import com.messageapp.api.security.JwtTokenProvider;
import com.messageapp.api.security.OtpService;
import com.messageapp.api.security.RoleEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements AuthService {

    private final OtpService otpService;
    private final RepositoryClass userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse verifyOtp(OtpDto dto) throws Throwable {

        // 1. validate OTP
        otpService.validateOtp(dto.getPhone(), dto.getOtp());

        // 2. fetch or create user
        user appUser = userRepository.findByPhone(dto.getPhone())
                .orElseGet(() ->
                        userRepository.save(
                                user.builder()
                                        .phone(dto.getPhone())
                                        .role(RoleEnum.USER)
                                        .active(true)
                                        .build()
                        )
                );

        if (!appUser.isActive()) {
            throw new Throwable("User is blocked");
        }

        // 3. generate JWT
        String token = jwtTokenProvider.generateToken(
                appUser.getPhone(),
                appUser.getRole()
        );

        // 4. response
        return AuthResponse.withToken(
                token,
                jwtTokenProvider.getExpirySeconds()
        );
    }
}
