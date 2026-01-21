package com.messageapp.api.security;

import com.messageapp.api.common.exceptions.ApiException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpServiceImple implements OtpService {

    private static final int OTP_EXPIRY_SECONDS = 300;

    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    @Override
    public void sendOtp(String phone) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStore.put(
                phone,
                new OtpData(otp, Instant.now().plusSeconds(OTP_EXPIRY_SECONDS))
        );

        // 🔥 Replace with SMS provider
        System.out.println("OTP for " + phone + " = " + otp);
    }

    @Override
    public void validateOtp(String phone, String otp) throws Throwable {

        OtpData data = otpStore.get(phone);

        if (data == null)
            throw new Throwable("OTP not generated");

        if (Instant.now().isAfter(data.expiry))
            throw new Throwable("OTP expired");

        if (!data.otp.equals(otp))
            throw new Throwable("Invalid OTP");

        // single-use
        otpStore.remove(phone);
    }

    private record OtpData(String otp, Instant expiry) {}
}
