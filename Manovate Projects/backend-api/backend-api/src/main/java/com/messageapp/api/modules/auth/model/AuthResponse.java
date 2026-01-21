package com.messageapp.api.modules.auth.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private boolean success;
    private String message;
    private String token;
    private long expiresIn;

    public static AuthResponse success(String msg) {
        return AuthResponse.builder()
                .success(true)
                .message(msg)
                .build();
    }

    public static AuthResponse withToken(String token, long expiry) {
        return AuthResponse.builder()
                .success(true)
                .token(token)
                .expiresIn(expiry)
                .build();
    }
}
