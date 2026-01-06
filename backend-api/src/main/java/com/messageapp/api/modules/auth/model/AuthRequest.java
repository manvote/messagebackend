package com.messageapp.api.modules.auth.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    private String phone;
    private String otp;
}
