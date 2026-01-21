package com.messageapp.api.security;

public interface OtpService {

    void sendOtp(String phone);

    void validateOtp(String phone, String otp) throws Throwable;
}
