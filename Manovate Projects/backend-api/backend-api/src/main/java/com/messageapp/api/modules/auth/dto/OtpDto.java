package com.messageapp.api.modules.auth.dto;

import lombok.Data;

@Data
public class OtpDto {
    private String phone;
    private String otp;
}
